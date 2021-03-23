package com.javairus.webnovel.web.dto.novels;

import com.javairus.webnovel.domain.novels.NovelDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class NovelDetailResponseDto {

    private Long novelDetailKey;
    private String detailTitle;
    private String reserve;
    private LocalDateTime reserveDate;
    private LocalDateTime regDate;
    private LocalDateTime modifyDate;

    public NovelDetailResponseDto(NovelDetail novelDetail) {

        this.novelDetailKey = novelDetail.getNovelDetailKey();
        this.detailTitle = novelDetail.getDetailTitle();
        this.reserve = novelDetail.getReserve();
        this.reserveDate = novelDetail.getReserveDate();
        this.regDate = novelDetail.getRegDate();
        this.modifyDate = novelDetail.getModifyDate();
    }
}
