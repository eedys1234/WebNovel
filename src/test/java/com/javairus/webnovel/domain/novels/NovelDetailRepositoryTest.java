package com.javairus.webnovel.domain.novels;

import com.javairus.webnovel.web.dto.novels.NovelDetailSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NovelDetailRepositoryTest {

    @Autowired
    NovelDetailRepository novelDetailRepository;

    @Autowired
    NovelMasterRepository novelMasterRepository;

    @After
    public void cleanUp() {
        novelMasterRepository.deleteAll();
    }

    @Test
    public void 웹소설_편수_등록하기() {

        //given
        String novelName = "테스트 웹소설";
        String subTitle = "테스트";
        String thumnailPath = "";
        String thumnailName = "";
        String monopoly = "0";
        String summary = "요약된";

        String detailTitle = "";
        String content = "소설의 시작은 다음과 같다.";
        String reserve = "0";

        //웹소설 데이터 insert
        NovelMaster novelMaster = novelMasterRepository.save(NovelMaster.builder()
                                                        .novelName(novelName)
                                                        .subTitle(subTitle)
                                                        .categoryCode(NovelMaster.CategoryCodeType.C0001)
                                                        .thumnailPath(thumnailPath)
                                                        .thumnailName(thumnailName)
                                                        .ageCode(NovelMaster.AgeCodeType.GREATED)
                                                        .novelCode(NovelMaster.NovelCodeType.GENERAL)
                                                        .monopoly(monopoly)
                                                        .summary(summary).build());


        NovelDetailSaveRequestDto requestDto = NovelDetailSaveRequestDto.builder()
                                                                    .detailTitle(detailTitle)
                                                                    .content(content)
                                                                    .reserve(reserve)
                                                                    .build();

        NovelDetail novelDetail = requestDto.toEntity();
        novelDetail.setNovelMaster(novelMaster);

        //when
        NovelDetail saveNovelDetail = novelDetailRepository.save(novelDetail);
        novelMaster.addDetail();

        //then
        assertThat(saveNovelDetail.getNovelDetailKey()).isGreaterThan(0L);
        assertThat(saveNovelDetail.getDetailTitle()).isEqualTo(detailTitle);
        assertThat(saveNovelDetail.getContent()).isEqualTo(content);
        assertThat(saveNovelDetail.getReserve()).isEqualTo(reserve);
        assertThat(novelMaster.getNovelNumbers()).isGreaterThan(0);
    }

    @Test
    public void 웹소설_편수_수정하기() {

        //given
        String novelName = "테스트 웹소설";
        String subTitle = "테스트";
        String thumnailPath = "";
        String thumnailName = "";
        String monopoly = "0";
        String summary = "요약된";

        String detailTitle = "";
        String content = "소설의 시작은 다음과 같다.";
        String reserve = "0";

        String updatedContent = "소설내용 변경";
        String updatedReserve = "1";
        LocalDateTime updatedReserveDate = LocalDateTime.now().plusHours(10);

        //웹소설 데이터 insert
        NovelMaster novelMaster = novelMasterRepository.save(NovelMaster.builder()
                .novelName(novelName)
                .subTitle(subTitle)
                .categoryCode(NovelMaster.CategoryCodeType.C0001)
                .thumnailPath(thumnailPath)
                .thumnailName(thumnailName)
                .ageCode(NovelMaster.AgeCodeType.GREATED)
                .novelCode(NovelMaster.NovelCodeType.GENERAL)
                .monopoly(monopoly)
                .summary(summary).build());

        //웹소설 편수 insert

        NovelDetail novelDetail = NovelDetail.builder()
                                .detailTitle(detailTitle)
                                .content(content)
                                .reserve(reserve)
                                .build();
        novelDetail.setNovelMaster(novelMaster);
        novelMaster.addDetail();

        NovelDetail saveNovelDetail = novelDetailRepository.save(novelDetail);

        //when
        saveNovelDetail.update(NovelDetail.builder()
                .content(updatedContent)
                .reserve(updatedReserve)
                .reserveDate(updatedReserveDate)
                .build());

        //then
        assertThat(saveNovelDetail.getContent()).isEqualTo(updatedContent);
        assertThat(saveNovelDetail.getReserve()).isEqualTo(updatedReserve);
        assertThat(saveNovelDetail.getReserveDate()).isEqualTo(updatedReserveDate);
        assertThat(novelMaster.getNovelNumbers()).isGreaterThan(0);
    }
}
