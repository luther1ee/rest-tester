package com.useb.tester.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Result {
    @JsonProperty("complete")
    private boolean complete;

    @JsonProperty("result_code")
    private String resultCode;

    @JsonProperty("key")
    private String key;

    @JsonProperty("frame_index")
    private int frameIndex;

    @JsonProperty("scanner_type")
    private String scannerType;

    @JsonProperty("result_scan_type")
    private String resultScanType;

    @JsonProperty("color")
    private boolean color;

    @JsonProperty("recog_time")
    private int recogTime;

    @JsonProperty("id")
    private IdCard idCard;

    @JsonProperty("analysis_info")
    private String analysisInfo;


}
