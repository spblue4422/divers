package com.spblue4422.divers.records;

import com.spblue4422.divers.Dto.BasicResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/records")
public class RecordController {
    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/my")
    public int getAllRecords() {
        return 0;
    }

    @GetMapping("/:id")
    public int getRecordDetail() {
        return 0;
    }

    @PostMapping("/add")
    public BasicResponseDto addRecord() {
        try {
            return BasicResponseDto.makeRes(null, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, "fail");
        }
    }
}
