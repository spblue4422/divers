package com.spblue4422.divers.records;

import com.spblue4422.divers.common.errors.BadRequestException;
import com.spblue4422.divers.common.services.ImageService;
import com.spblue4422.divers.dto.ImageInfoDto;
import com.spblue4422.divers.dto.records.AddRecordRequestDto;
import com.spblue4422.divers.dto.records.RecordListItemResponseDto;
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

//	public List<RecordListItemResponseDto> getRecordInfoList() {
//		return recordRepository.findAllRecords().orElseThrow(() -> new BadRequestException(400, "너는 null이니, 배열이니"));
//	}

	// 여기서 repository 함수를 두개 쓰지 말고, 배열까서 opened인 것만 남기는 방법도 있을듯?
//	public List<RecordListItemResponseDto> getRecordInfoListByUser(String userId, Boolean myself) {
//		if (myself) {return null;}
////			return recordRepository.findMyRecords(userId).orElseThrow(() -> new BadRequestException(400,
////					"잘못된 ID입니다."));
//		else
//			return recordRepository.findOthersRecords(userId).orElseThrow(() -> new BadRequestException(400, "잘못된 ID입니다."));
//	}

//	public Record getRecordInfo(Long id, String loginId, Boolean myself) {
//		Record resData = recordRepository.findRecordDetail(id).orElseThrow(() -> new BadRequestException(400, "존재하지 않는 로그"));
//
//		if(resData.getOpened() || loginId.equals(resData.getUser().getLoginId())) {
//			return resData;
//		} else {
//			throw new BadRequestException(403, "접근불가능한 로그입니다.");
//		}
//	}

	public Record insertRecord(AddRecordRequestDto req, List<MultipartFile> images) throws IOException {
		User userData = userRepository.findUserByLoginIdAndDeletedAtIsNull(req.getLoginId()).orElseThrow(() -> new BadRequestException(400, "ID 없음"));
		Spot spotData = spotRepository.findBySpotIdAndDeletedAtIsNull(req.getSpotId()).orElseThrow(()-> new BadRequestException(400, "존재하지 않는 spot입니다."));

		int count = recordRepository.findMyRecords(req.getLoginId()).orElseThrow(() -> new BadRequestException(400, "ID 없음")).size() + 1;

		//RecordPhoto가 빈 Record를 생성
		Record tmpRecord = recordRepository.save(req.toEntity(userData, spotData, count));

		List<RecordPhoto> rpList = new ArrayList<RecordPhoto>();

		for(MultipartFile image : images) {
			ImageInfoDto tmpImage = photoService.saveFile(image, imageDir);
			RecordPhoto tmpRP = recordPhotoRepository.save(
					RecordPhoto.builder()
							.record(tmpRecord)
							.originalName(tmpImage.getOriginalName())
							.savedName(tmpImage.getSavedName())
							.savedPath(tmpImage.getSavedPath())
							.createdAt(new Date())
							.deletedAt(null)
							.build()
			);
			rpList.add(tmpRP);
		}

		//사진 정보가 담긴 배열 넣고 아까 추가한 Record 업데이트
		return recordRepository.save(req.toUpdateEntity(tmpRecord.getRecordId(), userData, spotData, count, rpList));
	}

	public int updateRecord() {
		return 0;
	}

	public int deleteRecord() {
		return 0;
	}
}
