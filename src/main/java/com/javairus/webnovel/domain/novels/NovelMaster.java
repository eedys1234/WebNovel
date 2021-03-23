package com.javairus.webnovel.domain.novels;

import com.javairus.webnovel.domain.BaseEntity;
import com.javairus.webnovel.domain.enums.EnumModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
@Setter가 없는경우
무분별하게 작성하기 보다는 도메인 클래스의 특별한 이벤트가 발생했을 때 정의하기 위한 메소드를 작성하는 것이 직관적
의도와 사용범위를 짐작하게 할 수 있기 때문에
 */
@Getter
@NoArgsConstructor
@Entity
public class NovelMaster extends BaseEntity {

    @Id
    @GeneratedValue
    private Long novelKey;

    @Column(nullable = false)
    private String novelName;

    @Column(nullable = true)
    private String subTitle;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryCodeType categoryCode;

    public enum CategoryCodeType implements EnumModel {

        C0001("판타지"),
        C0002("하렘"),
        C0003("현대"),
        C0004("라이트노벨"),
        C0005("일상"),
        C0006("패러디"),
        C0007("로맨스"),
        C0008("BL"),
        C0009("무협");

        private String value;

        CategoryCodeType(String value) {
            this.value = value;
        }

        @Override
        public String getKey(){
            return name();
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    @Column(length = 70)
    private String thumnailPath;

    @Column(length = 50)
    private String thumnailName;

    private int novelNumbers;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeCodeType ageCode;

    public enum AgeCodeType implements EnumModel {

        PG12("12"),
        PG15("15"),
        XREATED("19"),
        GREATED("전체");

        private String value;

        AgeCodeType(String value) {
            this.value = value;
        }

        @Override
        public String getKey() {
            return name();
        }

        @Override
        public String getValue() {
            return value;
        }
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NovelCodeType novelCode;

    public enum NovelCodeType implements EnumModel {

        GENERAL("일반연재"),
        PLUS("플러스");

        private String value;

        NovelCodeType(String value) {
            this.value = value;
        }

        @Override
        public String getKey() {
            return name();
        }

        @Override
        public String getValue() {
            return value;
        }

    }

    @Column(nullable = false, length = 1)
    private String monopoly;

    @Column(nullable = false, length = 1)
    public String status;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String summary;

    @OneToMany(mappedBy = "novelMaster", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<NovelDetail> novelDetails;

    @Builder
    public NovelMaster(String novelName, String subTitle, CategoryCodeType categoryCode, String thumnailPath,
                       String thumnailName, AgeCodeType ageCode, NovelCodeType novelCode, String monopoly,
                       String summary) {

        this.novelName = novelName;
        this.subTitle = subTitle;
        this.categoryCode = categoryCode;
        this.thumnailPath = thumnailPath;
        this.thumnailName = thumnailName;
        this.ageCode = ageCode;
        this.novelCode = novelCode;
        this.monopoly = monopoly; //converter 필요
        this.summary = summary;
        this.status = "0";
        this.novelNumbers = 0;
        this.novelDetails = new ArrayList<>();
    }

    public void update(NovelMaster novelMaster) {

        if(!Objects.isNull(novelMaster.getNovelName())) {
            this.novelName = novelMaster.getNovelName();
        }

        if(!Objects.isNull(novelMaster.getSubTitle())) {
            this.subTitle = novelMaster.getSubTitle();
        }

        if(!Objects.isNull(novelMaster.getCategoryCode())) {
            this.categoryCode = novelMaster.getCategoryCode();
        }

        if(!Objects.isNull(novelMaster.getThumnailPath())) {
            this.thumnailPath = novelMaster.getThumnailPath();
        }

        if(!Objects.isNull(novelMaster.getThumnailName())) {
            this.thumnailName = novelMaster.getThumnailName();
        }

        if(!Objects.isNull(novelMaster.getAgeCode())) {
            this.ageCode = novelMaster.getAgeCode();
        }

        if(!Objects.isNull(novelMaster.getNovelCode())) {
            this.novelCode = novelMaster.getNovelCode();
        }

        if(!Objects.isNull(novelMaster.getMonopoly())) {
            this.monopoly = novelMaster.getMonopoly();
        }

        if(!Objects.isNull(novelMaster.getSummary())) {
            this.summary = novelMaster.getSummary();
        }

        if(!Objects.isNull(novelMaster.getNovelNumbers())) {
            this.novelNumbers = novelMaster.getNovelNumbers();
        }

        if(!Objects.isNull(novelMaster.getStatus())) {
            this.status = novelMaster.getStatus();
        }

    }
}
