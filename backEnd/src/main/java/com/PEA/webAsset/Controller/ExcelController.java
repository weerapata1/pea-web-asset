package com.PEA.webAsset.Controller;

import com.PEA.webAsset.Entity.Tutorial;
import com.PEA.webAsset.Repository.DeviceTypeRepository;
import com.PEA.webAsset.Repository.TutorialRepository;
import com.PEA.webAsset.Share.ExcelService.ExcelHelper;
import com.PEA.webAsset.Share.ExcelService.ExcelService;
import com.PEA.webAsset.Share.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    ExcelService excelService;
    @Autowired
    private
    TutorialRepository tutorialRepository;
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveExcel(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getTutorialAll(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "3") int size
                                                              ) throws IOException {
        String message = "";
        try {
            List<Tutorial> tutorialTemp = new ArrayList<Tutorial>();
            Pageable paging = PageRequest.of(page, size);

            message = "ok find all";
            Page<Tutorial> pageTuts = tutorialRepository.findAll(paging);
            tutorialTemp = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            response.put("data1", tutorialTemp);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            message = "Error : "+e.getMessage();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
