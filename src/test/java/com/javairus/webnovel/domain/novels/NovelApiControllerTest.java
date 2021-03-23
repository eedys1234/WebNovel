package com.javairus.webnovel.domain.novels;

import com.javairus.webnovel.web.dto.novels.NovelMasterSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelMasterUpdateRequestDto;
import com.javairus.webnovel.web.response.ApiResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NovelApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private NovelMasterRepository novelMasterRepository;

//    @Before
//    public void setUp() throws Exception {
//        restTemplate = new TestRestTemplate();
//    }

    @After
    public void tearDown() throws Exception {
        novelMasterRepository.deleteAll();
    }

    @Test
    public void 웹소설_등록하다() throws Exception {

        //given
        String novelName = "테스트_웹소설";
        String subTitle = "부제";
        String thumnailPath = "";
        String thumnailName = "";
        String summary = "요약";
        String monopoly = "0";
        NovelMaster.CategoryCodeType categoryCode = NovelMaster.CategoryCodeType.C0001;
        NovelMaster.AgeCodeType ageCode = NovelMaster.AgeCodeType.GREATED;
        NovelMaster.NovelCodeType novelCode = NovelMaster.NovelCodeType.GENERAL;

        NovelMasterSaveRequestDto requestDto = NovelMasterSaveRequestDto.builder()
                                                .novelName(novelName)
                                                .subTitle(subTitle)
                                                .thumnailPath(thumnailPath)
                                                .thumnailName(thumnailName)
                                                .summary(summary)
                                                .categoryCode(categoryCode)
                                                .ageCode(ageCode)
                                                .novelCode(novelCode)
                                                .monopoly(monopoly)
                                                .build();

        String url = "http://localhost:" + port + "/api/v1/novels/master" ;

        //when
        ResponseEntity<ApiResponse> entity = restTemplate.postForEntity(url, requestDto, ApiResponse.class);

        //then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat((Integer) entity.getBody().getObject()).isGreaterThan(0);

        List<NovelMaster> novelMasters = novelMasterRepository.findAll();
        assertThat(novelMasters.get(0).getNovelName()).isEqualTo(novelName);
        assertThat(novelMasters.get(0).getSubTitle()).isEqualTo(subTitle);
        assertThat(novelMasters.get(0).getThumnailPath()).isEqualTo(thumnailPath);
        assertThat(novelMasters.get(0).getThumnailName()).isEqualTo(thumnailName);
        assertThat(novelMasters.get(0).getSummary()).isEqualTo(summary);
        assertThat(novelMasters.get(0).getCategoryCode()).isEqualTo(categoryCode);
        assertThat(novelMasters.get(0).getAgeCode()).isEqualTo(ageCode);
        assertThat(novelMasters.get(0).getNovelCode()).isEqualTo(novelCode);
        assertThat(novelMasters.get(0).getMonopoly()).isEqualTo(monopoly);

    }

    @Test
    public void 소설을_수정하다() throws Exception {

        //given
        String novelName = "테스트_웹소설";
        String subTitle = "부제";
        String thumnailPath = "";
        String thumnailName = "";
        String summary = "요약";
        String monopoly = "0";
        NovelMaster.CategoryCodeType categoryCode = NovelMaster.CategoryCodeType.C0001;
        NovelMaster.AgeCodeType ageCode = NovelMaster.AgeCodeType.GREATED;
        NovelMaster.NovelCodeType novelCode = NovelMaster.NovelCodeType.GENERAL;


        NovelMaster novelMaster = novelMasterRepository.save(NovelMasterSaveRequestDto.builder()
                                                                            .novelName(novelName)
                                                                            .subTitle(subTitle)
                                                                            .thumnailPath(thumnailPath)
                                                                            .thumnailName(thumnailName)
                                                                            .summary(summary)
                                                                            .categoryCode(categoryCode)
                                                                            .ageCode(ageCode)
                                                                            .novelCode(novelCode)
                                                                            .monopoly(monopoly)
                                                                            .build().toEntity());

        String extendSubTitle = "부제2";
        long id = novelMaster.getNovelKey();

        NovelMasterUpdateRequestDto requestDto = NovelMasterUpdateRequestDto.builder().subTitle(extendSubTitle).build();

        String url = "http://localhost:" + port + "/api/v1/novels/master/" + id;

        HttpEntity<NovelMasterUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<ApiResponse> entity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, ApiResponse.class);

        //then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat((Integer) entity.getBody().getObject()).isGreaterThan(0);

        List<NovelMaster> novelMasters = novelMasterRepository.findAll();
        assertThat(novelMasters.get(0).getSubTitle()).isEqualTo(extendSubTitle);
        assertThat(novelMasters.get(0).getNovelName()).isEqualTo(novelName);
    }

    @Test
    public void 소설을_삭제하다() throws Exception {

        //given
        String novelName = "테스트_웹소설";
        String subTitle = "부제";
        String thumnailPath = "";
        String thumnailName = "";
        String summary = "요약";
        String monopoly = "0";
        NovelMaster.CategoryCodeType categoryCode = NovelMaster.CategoryCodeType.C0001;
        NovelMaster.AgeCodeType ageCode = NovelMaster.AgeCodeType.GREATED;
        NovelMaster.NovelCodeType novelCode = NovelMaster.NovelCodeType.GENERAL;

        NovelMasterSaveRequestDto requestDto = NovelMasterSaveRequestDto.builder()
                .novelName(novelName)
                .subTitle(subTitle)
                .thumnailPath(thumnailPath)
                .thumnailName(thumnailName)
                .summary(summary)
                .categoryCode(categoryCode)
                .ageCode(ageCode)
                .novelCode(novelCode)
                .monopoly(monopoly)
                .build();

        NovelMaster novelMaster = novelMasterRepository.save(requestDto.toEntity());

        long id = novelMaster.getNovelKey();
        String url = "http://localhost:" + port + "/api/v1/novels/master/" + id;

        //when
        ResponseEntity<ApiResponse> entity = restTemplate.exchange(url, HttpMethod.DELETE, null, ApiResponse.class);

        //then
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat((int)entity.getBody().getObject()).isGreaterThan(0);

        List<NovelMaster> novelMasters = novelMasterRepository.findAll();
        assertThat(novelMasters.size()).isEqualTo(0);

    }
}
