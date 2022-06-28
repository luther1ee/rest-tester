package com.useb.tester.app;

import com.useb.tester.domain.ApiCallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.CompletableFuture;

@Component
public class AppRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
    private final ApiCallService apiCallService;

    public AppRunner(ApiCallService apiCallService) {
        this.apiCallService = apiCallService;
    }

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();

        String filePath = "/Users/useb/idcard_ocr_test_image/";

        File dir = new File(filePath);
        String dirNames[] = dir.list();
        for(String name : dirNames){
            CompletableFuture<String> p01 = apiCallService.ocrTest(filePath + name);
            CompletableFuture.allOf(p01).join();
        }

        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
    }
}
