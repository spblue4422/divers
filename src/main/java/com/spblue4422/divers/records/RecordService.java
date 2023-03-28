package com.spblue4422.divers.records;

import com.spblue4422.divers.common.errors.BadRequestException;
import com.spblue4422.divers.dto.records.AddRecordRequestDto;
import com.spblue4422.divers.dto.records.RecordListItemResponseDto;
import com.spblue4422.divers.spots.Spot;
import com.spblue4422.divers.spots.SpotRepository;
import com.spblue4422.divers.users.User;
import com.spblue4422.divers.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
	private final UserRepository userRepository;
	private final SpotRepository spotRepository;
	private final RecordRepository recordRepository;

	@Autowired
	public RecordService(UserRepository userRepository, SpotRepository spotRepository, RecordRepository recordRepository) {
		this.userRepository = userRepository;
		this.spotRepository = spotRepository;
		this.recordRepository = recordRepository;
	}

	public List<RecordListItemResponseDto> getRecordInfoList() {
		return recordRepository.findAllRecords().orElseThrow(() -> new BadRequestException(400, "너는 null이니, 배열이니"));
	}

	// 여기서 repository 함수를 두개 쓰지 말고, 배열까서 opened인 것만 남기는 방법도 있을듯?
	public List<RecordListItemResponseDto> getRecordInfoListByUser(String userId, Boolean myself) {
		if (myself)
			return recordRepository.findMyRecords(userId).orElseThrow(() -> new BadRequestException(400,
					"잘못된 ID입니다."));
		else
			return recordRepository.findOthersRecords(userId).orElseThrow(() -> new BadRequestException(400, "잘못된 ID입니다."));
	}

	public Record getRecordInfo(Long id, String userId, Boolean myself) {
		Record resData = recordRepository.findRecordDetail(id).orElseThrow(() -> new BadRequestException(400, "존재하지 않는 로그"));

		if(resData.isOpened() || userId.equals(resData.getUser().getUserId())) {
			return resData;
		} else {
			throw new BadRequestException(403, "접근불가능한 로그입니다.");
		}
	}

	public Record insertRecord(AddRecordRequestDto req) {
		User userData = userRepository.findUserByUserIdAndDeletedAtIsNull(req.getUserId()).orElseThrow(() -> new BadRequestException(400, "ID 없음"));
		Spot spotData = spotRepository.findByIdAndDeletedAtIsNull(req.getSpotId()).orElseThrow(()-> new BadRequestException(400, "존재하지 않는 spot입니다."));

		int count = recordRepository.findMyRecords(req.getUserId()).orElseThrow(() -> new BadRequestException(400, "ID 없음")).size() + 1;

		return recordRepository.save(req.toEntity(userData, spotData, count));
	}

	public int updateRecord() {
		return 0;
	}

	public int deleteRecord() {
		return 0;
	}
}
