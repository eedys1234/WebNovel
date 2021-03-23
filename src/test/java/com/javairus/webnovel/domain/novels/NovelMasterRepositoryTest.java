package com.javairus.webnovel.domain.novels;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NovelMasterRepositoryTest {

    @Autowired
    NovelMasterRepository novelMasterRepository;

    @After
    public void cleanup() {
        novelMasterRepository.deleteAll();
    }

    @Test
    public void 웹소설_가져오기() {

        String novelName = "테스트 웹소설";
        String subTitle = "테스트";
        String thumnailPath = "";
        String thumnailName = "";
        String monopoly = "0";
        String summary = "요약된";

        //웹소설 데이터 insert
        novelMasterRepository.save(NovelMaster.builder()
                                                .novelName(novelName)
                                                .subTitle(subTitle)
                                                .categoryCode(NovelMaster.CategoryCodeType.C0001)
                                                .thumnailPath(thumnailPath)
                                                .thumnailName(thumnailName)
                                                .ageCode(NovelMaster.AgeCodeType.GREATED)
                                                .novelCode(NovelMaster.NovelCodeType.GENERAL)
                                                .monopoly(monopoly)
                                                .summary(summary).build());


        //when
        List<NovelMaster> novelMasters = novelMasterRepository.findAll();

        //then
        NovelMaster novelMaster = novelMasters.get(0);
        assertThat(novelMaster.getNovelName()).isEqualTo(novelName);
        assertThat(novelMaster.getSubTitle()).isEqualTo(subTitle);
        assertThat(novelMaster.getCategoryCode()).isEqualTo(NovelMaster.CategoryCodeType.C0001);
        assertThat(novelMaster.getThumnailPath()).isEqualTo(thumnailPath);
        assertThat(novelMaster.getThumnailName()).isEqualTo(thumnailName);
        assertThat(novelMaster.getAgeCode()).isEqualTo(NovelMaster.AgeCodeType.GREATED);
        assertThat(novelMaster.getNovelCode()).isEqualTo(NovelMaster.NovelCodeType.GENERAL);
        assertThat(novelMaster.getMonopoly()).isEqualTo(monopoly);
        assertThat(novelMaster.getSummary()).isEqualTo(summary);

    }

    @Test
    public void BaseEntity_테스트() {

        //given
        String novelName = "테스트 웹소설";
        String subTitle = "테스트";
        String thumnailPath = "";
        String thumnailName = "";
        String monopoly = "0";
        String summary = "요약된";
        LocalDateTime now = LocalDateTime.now();

        //웹소설 데이터 insert
        novelMasterRepository.save(NovelMaster.builder()
                .novelName(novelName)
                .subTitle(subTitle)
                .categoryCode(NovelMaster.CategoryCodeType.C0001)
                .thumnailPath(thumnailPath)
                .thumnailName(thumnailName)
                .ageCode(NovelMaster.AgeCodeType.GREATED)
                .novelCode(NovelMaster.NovelCodeType.GENERAL)
                .monopoly(monopoly)
                .summary(summary).build());

        //when
        List<NovelMaster> novelMasters = novelMasterRepository.findAll();

        //then
        NovelMaster novelMaster = novelMasters.get(0);
        assertThat(novelMaster.getRegDate()).isAfter(now);
        assertThat(novelMaster.getModifyDate()).isAfter(now);

    }
}
