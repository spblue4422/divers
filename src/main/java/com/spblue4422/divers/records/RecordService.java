package com.spblue4422.divers.records;

import com.spblue4422.divers.common.errors.BadRequestException;
import com.spblue4422.divers.dto.records.AddRecordRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private RecordRepository recordRepository;

    @Autowired
    public RecordService (RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public List<Record> getAllRecordsByUser(String userId) {
        return recordRepository.findMyRecordsDeletedAtNull(userId).orElseThrow(()-> new BadRequestException(400, "잘못된 ID입니다."));
    }

    public Record insertRecord(AddRecordRequestDto req) {
        return recordRepository.save(req.toEntity());
    }

    public int updateRecord() {
        return 0;
    }

    public int deleteRecord() {
        return 0;
    }
}
