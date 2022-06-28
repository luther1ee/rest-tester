package com.useb.tester.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "result")
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "complete")
    private boolean compelte;
    @Column(name = "result_code")
    private String resultCode;
    @Column(name = "key_value")
    private String key;
    @Column(name = "frame_index")
    private int frameIndex;
    @Column(name = "scannerType")
    private String scannerType;
    @Column(name = "result_scan_type")
    private String resultScanType;
    @Column(name = "color")
    private boolean color;
    @Column(name = "recog_time")
    private int recogTime;
    @Column(name = "analysis_info")
    private String analysisInfo;

    @Column(name = "name")
    private String name;
    @Column(name = "jumin")
    private String jumin;
    @Column(name = "issued_date")
    private String issuedDate;
    @Column(name = "region")
    private String region;
    @Column(name = "validation")
    private boolean validation;
    @Column(name = "found_face")
    private float foundFace;
    @Column(name = "face_recog_time")
    private int faceRecogTime;
    @Column(name = "specular_ratio")
    private float specularRatio;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "driver_type")
    private String driverType;
    @Column(name = "driver_number")
    private String driverNumber;
    @Column(name = "driver_serial")
    private String driverSerial;
    @Column(name = "expatriate")
    private String expatriate;
}
