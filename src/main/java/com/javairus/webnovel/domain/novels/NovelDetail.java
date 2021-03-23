package com.javairus.webnovel.domain.novels;


import com.javairus.webnovel.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class NovelDetail extends BaseEntity {

    @Id
    @GeneratedValue
    private Long novelDetailKey;

    @Column(length = 50)
    private String detailTitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 1, nullable = false)
    private String reserve;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime reserveDate;

    //다대일 관계설정
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "novelKey")
    private NovelMaster novelMaster;

    @Builder
    public NovelDetail(String detailTitle, String content, String reserve, LocalDateTime reserveDate) {
        this.detailTitle = detailTitle;
        this.content = content;
        this.reserve = reserve;
        this.reserveDate = reserveDate;
    }

    //연관관계에서 양방향 설정
    public void setNovelMaster(NovelMaster novelMaster) {

        this.novelMaster = novelMaster;

        if(!Objects.isNull(novelMaster)) {
            this.novelMaster.getNovelDetails().add(this);
        }
    }

    public void standBy() {
        this.novelMaster = null;
    }

    public void update(NovelDetail novelDetail) {

        if(!Objects.isNull(novelDetail.getDetailTitle())) {
            this.detailTitle = novelDetail.getDetailTitle();
        }

        if(!Objects.isNull(novelDetail.getContent())) {
            this.content = novelDetail.getContent();
        }

        if(!Objects.isNull(novelDetail.getReserve())) {
            this.reserve = novelDetail.getReserve();
        }

        if(!Objects.isNull(novelDetail.getReserveDate())) {
            this.reserveDate = novelDetail.getReserveDate();
        }

    }
}
