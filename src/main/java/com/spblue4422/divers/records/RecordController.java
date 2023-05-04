package com.spblue4422.divers.records;

import com.spblue4422.divers.dto.BasicResponseDto;
import com.spblue4422.divers.dto.records.RecordListResponseDto;
import com.spblue4422.divers.dto.records.RecordResponseDto;
import com.spblue4422.divers.dto.records.SaveRecordRequestDto;
import com.spblue4422.divers.dto.records.RecordListItemInfo;
//import com.spblue4422.divers.dto.records.UpdateRecordRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/myList")
    public BasicResponseDto getMyAllRecords(String loginId) {
        try {
            List<RecordListResponseDto> resData = recordService.getRecordInfoListByUser(loginId, true);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/list")
    public BasicResponseDto getAllRecords() {
        try {
            List<RecordListResponseDto> resData = recordService.getAllRecordInfoList();

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/list/{loginId}")
    public BasicResponseDto getOthersAllRecords(@PathVariable("loginId") String loginId) {
        try {
            List<RecordListResponseDto> resData = recordService.getRecordInfoListByUser(loginId, false);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/{recordId}")
    public BasicResponseDto getRecordDetail(@PathVariable("recordId") Long recordId) {
        try {
            RecordResponseDto resData = recordService.getRecordInfo(recordId, "spblue4422");

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @PostMapping("/add")
    public BasicResponseDto addRecord(@RequestPart(value="recordData") SaveRecordRequestDto req, @RequestPart(value="images") List<MultipartFile> images, @RequestPart(value="loginId") String loginId) {
        try {
            Long resultId = recordService.insertRecord(req, images, loginId);

            return BasicResponseDto.makeRes(resultId, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @PatchMapping("/modify")
    public BasicResponseDto modifyRecord(@RequestPart(value="recordData")SaveRecordRequestDto req, @RequestPart(value="images") List<MultipartFile> images, @RequestPart(value="loginId") String loginId) {
        try {
            Long resultId = recordService.updateRecord(req, images, loginId);

            return BasicResponseDto.makeRes(resultId, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @DeleteMapping("/remove/{recordId}")
    public BasicResponseDto removeRecord(@PathVariable("recordId") Long recordId) {
        try {
            Long resultId = recordService.deleteRecord(recordId, "spblue4422");

            return BasicResponseDto.makeRes(resultId, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }
}
