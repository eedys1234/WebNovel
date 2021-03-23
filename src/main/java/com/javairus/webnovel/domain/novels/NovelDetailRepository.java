package com.javairus.webnovel.domain.novels;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface NovelDetailRepository extends JpaRepository<NovelDetail, Long> {
    List<NovelDetail> findAllByRegDate(Pageable pageable);
}
