package com.javairus.webnovel.domain.enums;

import lombok.Getter;

/*
OCP에 근거하여 추상화되어 있는 EnumModel에 의존함으로써 수정을 최소화함
 */
@Getter
public class EnumValue {

    private String key;
    private String value;

    public EnumValue(EnumModel enumModel) {

        this.key = enumModel.getKey();
        this.value = enumModel.getValue();
    }
}
