package com.javairus.webnovel;

import com.javairus.webnovel.domain.enums.EnumMapper;
import com.javairus.webnovel.domain.novels.NovelMaster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EnumMapper create() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put("categorys", NovelMaster.CategoryCodeType.class);
        enumMapper.put("ages", NovelMaster.AgeCodeType.class);
        enumMapper.put("novelCodes", NovelMaster.NovelCodeType.class);

        return enumMapper;
    }

}
