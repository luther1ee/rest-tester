package com.useb.tester.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.useb.tester.domain.dto.DriverLicense;
import com.useb.tester.domain.dto.IdCard;
import com.useb.tester.domain.dto.ResidentRegistration;
import com.useb.tester.domain.dto.Result;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;

@Service
public class ApiCallService {

    private static final Logger logger = LoggerFactory.getLogger(ApiCallService.class);

    private ObjectMapper objectMapper;

    private ResultRepository resultRepository;

    public ApiCallService(ObjectMapper objectMapper, ResultRepository resultRepository) {
        this.objectMapper = objectMapper;
        this.resultRepository = resultRepository;
    }

    public CompletableFuture<String> ocrTest(String filePath) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        MultipartFile mFIle = getMultipartFile(filePath);

        body.add("files", new ByteArrayResource(mFIle.getBytes()) {
            @Override
            public String getFilename() {
                return mFIle.getOriginalFilename();
            }
        });

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        final String ocrUrl = "http://3.35.3.47:9091/scan/id-auto";
        ResponseEntity<String> result = restTemplate.postForEntity(ocrUrl, entity, String.class);
        try{
            Result jsonResult = objectMapper.readValue(result.getBody(), Result.class);
            IdCard idCard = jsonResult.getIdCard();
            if (idCard != null) {
                DriverLicense driverLicense = idCard.getDriverLicense();
                if (driverLicense == null) {
                    driverLicense = new DriverLicense();
                }
                ResidentRegistration residentRegistration = idCard.getResidentRegistration();
                if (residentRegistration == null) {
                    residentRegistration = new ResidentRegistration();
                }

                ResultEntity resultEntity = ResultEntity.builder()
                        .compelte(jsonResult.isComplete())
                        .resultCode(jsonResult.getResultCode())
                        .key(jsonResult.getKey())
                        .frameIndex(jsonResult.getFrameIndex())
                        .scannerType(jsonResult.getScannerType())
                        .resultScanType(jsonResult.getResultScanType())
                        .color(jsonResult.isColor())
                        .recogTime(jsonResult.getRecogTime())
                        .name(idCard.getName())
                        .jumin(idCard.getJumin())
                        .issuedDate(idCard.getIssuedDate())
                        .region(idCard.getRegion())
                        .validation(idCard.isValidation())
                        .foundFace(idCard.getFoundFace())
                        .faceRecogTime(idCard.getFaceRecogTime())
                        .specularRatio(idCard.getSpecularRatio())
                        .driverNumber(driverLicense.getDriverNumber())
                        .driverType(driverLicense.getDriverType())
                        .driverSerial(driverLicense.getDriverSerial())
                        .expatriate(residentRegistration.getExpatriate())
                        .fileName(mFIle.getOriginalFilename())
                        .build();

                resultRepository.save(resultEntity);
            }else {
                ResultEntity resultEntity = ResultEntity.builder()
                        .compelte(jsonResult.isComplete())
                        .resultCode(jsonResult.getResultCode())
                        .key(jsonResult.getKey())
                        .frameIndex(jsonResult.getFrameIndex())
                        .scannerType(jsonResult.getScannerType())
                        .resultScanType(jsonResult.getResultScanType())
                        .color(jsonResult.isColor())
                        .recogTime(jsonResult.getRecogTime())
                        .fileName(mFIle.getOriginalFilename())
                        .build();
                resultRepository.save(resultEntity);
            }
        }catch (Exception e) {
            ResultEntity resultEntity = ResultEntity.builder()
                    .compelte(false)
                    .fileName(mFIle.getOriginalFilename())
                    .build();
            resultRepository.save(resultEntity);
        }


        return CompletableFuture.completedFuture(result.getBody());
    }

    private MultipartFile getMultipartFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        InputStream input = new FileInputStream(file);
        OutputStream os = fileItem.getOutputStream();

        IOUtils.copy(input, os);
        return new CommonsMultipartFile(fileItem);
    }
}
