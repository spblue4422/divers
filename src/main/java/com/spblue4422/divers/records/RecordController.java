package com.spblue4422.divers.records;

import com.spblue4422.divers.dto.BasicResponseDto;
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
            List<RecordListItemInfo> resData = recordService.getRecordInfoListByUser(loginId, true);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/list")
    public BasicResponseDto getAllRecords() {
        try {
            List<RecordListItemInfo> resData = recordService.getAllRecordInfoList();

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @GetMapping("/list/:loginId")
    public BasicResponseDto getOthersAllRecords(String loginId) {
        try {
            List<RecordListItemInfo> resData = recordService.getRecordInfoListByUser(loginId, false);

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
    public BasicResponseDto addRecord(@RequestPart(value="recordData") SaveRecordRequestDto req, @RequestPart(value="images") List<MultipartFile> images, @RequestPart(value="loginId") String loginId) {
        try {
            Record resData = recordService.insertRecord(req, images, loginId);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @PatchMapping("/modify")
    public BasicResponseDto modifyRecord(@RequestPart(value="recordData")SaveRecordRequestDto req, @RequestPart(value="images") List<MultipartFile> images, @RequestPart(value="loginId") String loginId) {
        try {
            Record resData = recordService.updateRecord(req, images, loginId);

            return BasicResponseDto.makeRes(resData, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public BasicResponseDto removeRecord(Long recordId, String loginId) {
        try {
            int status = recordService.deleteRecord(recordId, loginId);

            return BasicResponseDto.makeRes(status, 200, "success");
        } catch(Exception ex) {
            return BasicResponseDto.makeRes(null, 500, ex.getMessage());
        }
    }
}
