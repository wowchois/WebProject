package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelService excelService;

    @PostMapping(value = "/excel/parse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> excelParseTest(@RequestPart("file") MultipartFile file){
        Map<String,Object> result = new HashMap<>();

        log.info("file name : {}", file.getOriginalFilename());

        excelService.readToList(file);

        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/excel/sheetparse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> excelParsingTest(@RequestPart("file") MultipartFile file){
        Map<String,Object> result = new HashMap<>();

        log.info("file name : {}", file.getOriginalFilename());

        excelService.readToListMap(file);

        return ResponseEntity.ok(result);
    }

}
