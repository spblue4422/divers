package com.spblue4422.divers.records;

import com.spblue4422.divers.dto.BasicResponseDto;
import com.spblue4422.divers.dto.records.AddRecordRequestDto;
import com.spblue4422.divers.dto.records.RecordListItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public BasicResponseDto getMyAllRecords(String loginId) {
        try {
            List<RecordListItemResponseDto> resData = null;//recordService.getRecordInfoListByUser(loginId, true);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/list/:loginId")
    public BasicResponseDto getOthersAllRecords(String loginId) {
        try {
            List<RecordListItemResponseDto> resData = null;//recordService.getRecordInfoListByUser(loginId, false);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/:recordId")
    public int getRecordDetail() {
        return 0;
    }

    @PostMapping("/add")
    public BasicResponseDto addRecord(@RequestPart(value="recordData") AddRecordRequestDto req, @RequestPart(value="images") List<MultipartFile> images) {
        try {
            Record resData = recordService.insertRecord(req, images);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }
}
