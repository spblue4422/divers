package com.spblue4422.divers.records;

import com.spblue4422.divers.common.errors.BadRequestException;
import com.spblue4422.divers.common.services.ImageService;
import com.spblue4422.divers.dto.ImageInfoDto;
import com.spblue4422.divers.dto.records.*;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.spots.SpotRepository;
import com.spblue4422.divers.users.User;
import com.spblue4422.divers.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RecordService {
	private final String imageDir = "C:/Users/user/Desktop/code/divers/images/records/";
	private final UserRepository userRepository;
	private final SpotRepository spotRepository;
	private final RecordRepository recordRepository;
	private final RecordPhotoRepository recordPhotoRepository;
	private final ImageService photoService;

	@Autowired
	public RecordService(UserRepository userRepository, SpotRepository spotRepository, RecordRepository recordRepository, RecordPhotoRepository recordPhotoRepository, ImageService photoService) {
		this.userRepository = userRepository;
		this.spotRepository = spotRepository;
		this.recordRepository = recordRepository;
		this.recordPhotoRepository = recordPhotoRepository;
		this.photoService = photoService;
	}

	public List<RecordListResponseDto> getAllRecordInfoList() {
		//return recordRepository.findAllRecords().orElseThrow(() -> new BadRequestException(400, "너는 null이니, 배열이니"));
		List<Record> recordDataList = recordRepository.findAllByDeletedAtIsNull().orElseThrow(() -> new BadRequestException(400, "너는 null이니, 배열이니"));
		List<RecordListResponseDto> resDataList = new ArrayList<>();

		for(Record recordData: recordDataList) {
			resDataList.add(recordData.toRecordListResponseDto());
		}

		return resDataList;
	}

	// 여기서 repository 함수를 두개 쓰지 말고, 배열까서 opened인 것만 남기는 방법도 있을듯?
	public List<RecordListResponseDto> getRecordInfoListByUser(String userId, Boolean myself) {
		List<Record> recordDataList = recordRepository.findRecordsByLoginId(userId).orElseThrow(() -> new BadRequestException(400, "잘못된 ID입니다."));
		List<RecordListResponseDto> resDataList = new ArrayList<>();

		for(Record recordData: recordDataList) {
			resDataList.add(recordData.toRecordListResponseDto());
		}

		if (!myself) {
			resDataList.removeIf((data) -> (!(data.getOpened())));
		}

		return resDataList;
	}

	public RecordResponseDto getRecordInfo(Long recordId, String loginId) {
		Record recordData = recordRepository.findByRecordIdAndDeletedAtIsNull(recordId).orElseThrow(() -> new BadRequestException(400, "존재하지 않는 로그"));

		List<RecordPhotoResponseDto> photoResDataList = new ArrayList<>();

		for(RecordPhoto rp: recordData.getRecordPhotoList()) {
			photoResDataList.add(new RecordPhotoResponseDto(rp.getSavedPath(), rp.getPhotoOrder()));
		}

		if(recordData.getOpened() || loginId.equals(recordData.getUser().getLoginId())) {
			return recordData.toRecordResponseDto(photoResDataList);
		} else {
			throw new BadRequestException(403, "접근불가능한 로그입니다.");
		}
	}

	//sTemeperature와 wTemperature가 들어가지 않는이유??? 디버그로 찍어보자.
	public RecordResponseDto insertRecord(SaveRecordRequestDto req, List<MultipartFile> images, String loginId) throws IOException {
		User userData = userRepository.findUserByLoginIdAndDeletedAtIsNull(loginId).orElseThrow(() -> new BadRequestException(400, "ID 없음"));
		Spot spotData = spotRepository.findBySpotIdAndDeletedAtIsNull(req.getSpotId()).orElseThrow(()-> new BadRequestException(400, "존재하지 않는 spot입니다."));

		int count = recordRepository.findRecordsByLoginId(loginId).orElseThrow(() -> new BadRequestException(400, "ID 없음")).size() + 1;

		//RecordPhoto가 빈 Record를 생성
		Record newRecord = recordRepository.save(req.toInsertEntity(userData, spotData, count));

		List<RecordPhoto> rpList = new ArrayList<>();
		List<RecordPhotoResponseDto> photoResDataList = new ArrayList<>();

		for(MultipartFile image : images) {
			RecordPhoto newRecordPhoto = insertRecordPhoto(newRecord, image, images.indexOf(image) + 1);
			rpList.add(newRecordPhoto);
			photoResDataList.add(new RecordPhotoResponseDto(newRecordPhoto.getSavedPath(), newRecordPhoto.getPhotoOrder()));
		}

		//사진 정보가 담긴 배열 넣고 아까 추가한 Record 업데이트
		//여기서 바로 return 때리면 좀 곤란... 외부로 전달할 dto에 담아 전달하는게 나을듯 - RecordResponseDto
		return recordRepository.save(req.toUpdateEntity(newRecord.getRecordId(), userData, spotData, count, rpList)).toRecordResponseDto(photoResDataList);
	}

	public RecordResponseDto updateRecord(SaveRecordRequestDto req, List<MultipartFile> images, String loginId) throws IOException {
		User userData = userRepository.findUserByLoginIdAndDeletedAtIsNull(loginId).orElseThrow(() -> new BadRequestException(400, "ID 없음"));

		Record recordData = recordRepository.findByRecordIdAndDeletedAtIsNull(req.getRecordId()).orElseThrow(()-> new BadRequestException(400, "존재하지 않는 로그입니다."));

		//파일 delete 처리 하는부분
		for(RecordPhoto rp : recordData.getRecordPhotoList()) {
			RecordPhoto deletedRecordPhoto = recordPhotoRepository.save(
					RecordPhoto.builder()
							.recordPhotoId(rp.getRecordPhotoId())
							.record(rp.getRecord())
							.originalName(rp.getOriginalName())
							.savedName(rp.getSavedName())
							.savedPath(rp.getSavedPath())
							.photoOrder(rp.getPhotoOrder())
							.createdAt(rp.getCreatedAt())
							.deletedAt(new Date())
							.build()
			);
		}

		List<RecordPhoto> rpList = new ArrayList<>();
		List<RecordPhotoResponseDto> photoResDataList = new ArrayList<>();

		for(MultipartFile image : images) {
			RecordPhoto newRecordPhoto = insertRecordPhoto(recordData, image, images.indexOf(image) + 1);
			rpList.add(newRecordPhoto);
			photoResDataList.add(new RecordPhotoResponseDto(newRecordPhoto.getSavedPath(), newRecordPhoto.getPhotoOrder()));
		}

		return recordRepository.save(req.toUpdateEntity(recordData.getRecordId(), userData, recordData.getSpot(), recordData.getLogNo(), rpList)).toRecordResponseDto(photoResDataList);
	}

	public int deleteRecord(Long recordId, String loginId) {
		Record recordData = recordRepository.findByRecordIdAndDeletedAtIsNull(recordId).orElseThrow(()-> new BadRequestException(400, "존재하지 않는 로그입니다."));

		if(!recordData.getUser().getLoginId().equals(loginId)) {
			throw new BadRequestException(400, "삭제할 권한이 없는 로그입니다.");
		}

		recordRepository.delete(recordData);

		return 0;
	}

	public RecordPhoto insertRecordPhoto(Record recordData, MultipartFile image, int order) throws IOException {
		ImageInfoDto newImage = photoService.saveFile(image, imageDir);
		return recordPhotoRepository.save(
				RecordPhoto.builder()
						   .record(recordData)
						   .originalName(newImage.getOriginalName())
						   .savedName(newImage.getSavedName())
						   .savedPath(newImage.getSavedPath())
						   .photoOrder(order)
						   .createdAt(new Date())
						   .deletedAt(null)
						   .build()
		);
	}
}
