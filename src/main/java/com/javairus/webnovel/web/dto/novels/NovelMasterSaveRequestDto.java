package com.javairus.webnovel.web.dto.novels;

import com.javairus.webnovel.domain.novels.NovelMaster;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@NoArgsConstructor
@Getter
public class NovelMasterSaveRequestDto {

    @NotBlank(message = "작품명은 필수 입력 값입니다.")
    private String novelName;

    private String subTitle;

    @NotBlank(message = "카테고리 분류는 필수 입력 값입니다.")
    private NovelMaster.CategoryCodeType categoryCode;

    private String thumnailPath;

    private String thumnailName;

    @NotBlank(message = "연령분류는 필수 입력 값입니다.")
    private NovelMaster.AgeCodeType ageCode;

    @NotBlank(message = "소설 코드는 필수 입력 값입니다.")
    private NovelMaster.NovelCodeType novelCode;

    @NotBlank(message = "독점여부는 필수 입력 값입니다.")
    private String monopoly;

    private String summary;

    @Builder
    public NovelMasterSaveRequestDto(String novelName, String subTitle, NovelMaster.CategoryCodeType categoryCode, String thumnailPath,
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
