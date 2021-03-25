package com.javairus.webnovel.domain.novels;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class NovelCategory {

    @Id
    @GeneratedValue
    private Long categoryId;

    private String categoryCode;

    private String categoryName;

    @Builder
    public NovelCategory(String categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }

}
