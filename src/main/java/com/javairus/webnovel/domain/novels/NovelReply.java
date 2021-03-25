package com.javairus.webnovel.domain.novels;

import com.javairus.webnovel.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class NovelReply extends BaseEntity {

    @Id
    @GeneratedValue
    private Long novelReplyKey;

    private String content;

    private Long userId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "novelDetailKey")
    private NovelDetail novelDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentKey")
    private NovelReply novelReply;

    @OneToMany(mappedBy = "novelReply", cascade = CascadeType.REMOVE)
    private List<NovelReply> replys = new ArrayList<>();

    private int depth;

    private String isLive;

    @Builder
    public NovelReply(String content, Long userId, int depth) {
        this.content = content;
        this.userId = userId;
        this.depth = depth;
        this.isLive = "1";
    }

    public void setNovelDetail(NovelDetail novelDetail) {
        if(!Objects.isNull(novelDetail)) {
            this.novelDetail = novelDetail;
            this.novelDetail.getNovelReplys().add(this);
        }
    }

    public void standby() {
        this.novelDetail = null;
    }

    public void delete() {
        this.isLive = "0";
    }

    public void update(NovelReply novelReply) {

        if(!Objects.isNull(novelReply.getContent())) {
            this.content = novelReply.getContent();
        }
    }
}
