package com.javairus.webnovel.web.dto.novels;

import com.javairus.webnovel.domain.novels.NovelMaster;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NovelMasterUpdateRequestDto {

    private String novelName;
    private String subTitle;
    private NovelMaster.CategoryCodeType categoryCode;
    private String thumnailPath;
    private String thumnailName;
    private NovelMaster.AgeCodeType ageCode;
    private NovelMaster.NovelCodeType novelCode;
    private String monopoly;
    private String summary;

    @Builder
    public NovelMasterUpdateRequestDto(String novelName, String subTitle, NovelMaster.CategoryCodeType categoryCode, String thumnailPath,
                                     String thumnailName, NovelMaster.AgeCodeType ageCode, NovelMaster.NovelCodeType novelCode, String monopoly,
                                     String summary) {

        this.novelName = novelName;
        this.subTitle = subTitle;
        this.categoryCode = categoryCode;
        this.thumnailPath = thumnailPath;
        this.thumnailName = thumnailName;
        this.ageCode = ageCode;
        this.monopoly = monopoly;
        this.summary = summary;
        this.novelCode = novelCode;

    }

    //Adapter Pattern을 이용하여 A -> B 클래스로 변환
    public NovelMaster toEntity() {
        return NovelMaster.builder()
                .novelName(novelName)
                .subTitle(subTitle)
                .categoryCode(categoryCode)
                .thumnailPath(thumnailPath)
                .thumnailName(thumnailName)
                .ageCode(ageCode)
                .monopoly(monopoly)
                .summary(summary)
                .novelCode(novelCode)
                .build();
    }
}
