package com.spblue4422.divers.records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {
    private RecordRepository recordRepository;

    @Autowired
    public RecordService (RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public int insertRecord() {
        return 0;
    }

    public int updateRecord() {
        return 0;
    }

    public int deleteRecord() {
        return 0;
    }
}
