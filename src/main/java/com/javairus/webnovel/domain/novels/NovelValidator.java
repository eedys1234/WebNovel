package com.javairus.webnovel.domain.novels;

import com.javairus.webnovel.web.dto.novels.NovelDetailSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelDetailUpdateRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelMasterSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelMasterUpdateRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Objects;

@Component
public class NovelValidator {

    public void validate(NovelMasterSaveRequestDto novelMaster, Errors errors) {

    }

    public void validate(NovelMasterUpdateRequestDto novelMaster, Errors errors) {

    }

    public void validate(NovelDetailSaveRequestDto novelDetail, Errors errors) {

        if("1".equals(novelDetail.getReserve()) && Objects.isNull(novelDetail.getReserveDate())) {
            errors.rejectValue("reserveDate", "400", "예약일자 입력값이 누락되었습니다.");
        }
    }

    public void validate(NovelDetailUpdateRequestDto novelDetail, Errors errors) {

        if("1".equals(novelDetail.getReserve()) && Objects.isNull(novelDetail.getReserveDate())) {
            errors.rejectValue("reserveDate", "400", "예약일자 입력값이 누락되었습니다.");
        }
    }
}
