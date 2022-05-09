package com.base.jpaproject.main.controller;

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
public class ExcelController {

    @PostMapping(value = "/excel/parse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> excelParseTest(@RequestPart("file") MultipartFile file){
        Map<String,Object> result = new HashMap<>();

        log.info("file name : {}", file.getOriginalFilename());

        return ResponseEntity.ok(result);
    }
}
