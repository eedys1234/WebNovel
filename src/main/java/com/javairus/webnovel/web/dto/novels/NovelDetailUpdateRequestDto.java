package com.javairus.webnovel.web.dto.novels;

import com.javairus.webnovel.domain.novels.NovelDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class NovelDetailUpdateRequestDto {

    private String detailTitle;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    @NotBlank(message = "예약여부는 필수 입력값입니다.")
    private String reserve;

    private LocalDateTime reserveDate;

    @Builder
    public NovelDetailUpdateRequestDto(String detailTitle, String content, String reserve, LocalDateTime reserveDate) {
        this.detailTitle = detailTitle;
        this.content = content;
        this.reserve = reserve;
        this.reserveDate = reserveDate;
    }

    public NovelDetail toEntity() {
        return NovelDetail.builder()
                .detailTitle(detailTitle)
                .content(content)
                .reserve(reserve)
                .reserveDate(reserveDate)
                .build();
    }
}
