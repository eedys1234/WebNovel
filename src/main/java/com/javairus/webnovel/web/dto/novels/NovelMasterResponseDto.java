package com.javairus.webnovel.web.dto.novels;

import com.javairus.webnovel.domain.novels.NovelMaster;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class NovelMasterResponseDto {

    private Long novelKey;
    private String novelName;
    private String subTitle;
    private NovelMaster.CategoryCodeType categoryCode;
    private String thumnailPath;
    private String thumnailName;
    private NovelMaster.AgeCodeType ageCode;
    private NovelMaster.NovelCodeType novelCode;
    private String monopoly;
    private String summary;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;
    private Integer novelNumbers;
    private String status;

    //entity를 전달받아 변환작업
    public NovelMasterResponseDto(NovelMaster novelMaster) {

        this.novelKey = novelMaster.getNovelKey();
        this.novelName = novelMaster.getNovelName();
        this.subTitle = novelMaster.getSubTitle();
        this.categoryCode = novelMaster.getCategoryCode();
        this.thumnailPath = novelMaster.getThumnailPath();
        this.thumnailName = novelMaster.getThumnailName();
        this.ageCode = novelMaster.getAgeCode();
        this.novelCode = novelMaster.getNovelCode();
        this.monopoly = novelMaster.getMonopoly();
        this.summary = novelMaster.getSummary();
        this.regDate = novelMaster.getRegDate();
        this.modifyDate = novelMaster.getModifyDate();
        this.novelNumbers = novelMaster.getNovelNumbers();
        this.status = novelMaster.getStatus();
    }
}
