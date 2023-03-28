package com.spblue4422.divers.records;

import com.spblue4422.divers.dto.BasicResponseDto;
import com.spblue4422.divers.dto.records.AddRecordRequestDto;
import com.spblue4422.divers.dto.records.RecordListItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/my")
    public BasicResponseDto getMyAllRecords(String userId) {
        try {
            List<RecordListItemResponseDto> resData = recordService.getAllRecordsByUser(userId, true);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/:userId")
    public BasicResponseDto getOthersAllRecords(String userId) {
        try {
            List<RecordListItemResponseDto> resData = recordService.getAllRecordsByUser(userId, false);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/:id")
    public int getRecordDetail() {
        return 0;
    }

    @PostMapping("/add")
    public BasicResponseDto addRecord(AddRecordRequestDto req) {
        try {
            return BasicResponseDto.makeRes(null, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, "fail");
        }
    }
}
