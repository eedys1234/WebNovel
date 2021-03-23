package com.javairus.webnovel.service;

import com.javairus.webnovel.domain.novels.NovelMaster;
import com.javairus.webnovel.domain.novels.NovelMasterRepository;
import com.javairus.webnovel.web.dto.novels.NovelMasterResponseDto;
import com.javairus.webnovel.web.dto.novels.NovelMasterSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelMasterUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class NovelMasterService {

    private final NovelMasterRepository novelMasterRepository;

    @Transactional
    public Long save(NovelMasterSaveRequestDto requestDto) {
        return novelMasterRepository.save(requestDto.toEntity()).getNovelKey();
    }

    @Transactional
    public Long update(long id, NovelMasterUpdateRequestDto requestDto) {

        NovelMaster novelMaster = novelMasterRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("소설을 찾을 수 없습니다. {id}=" + id));

        novelMaster.update(requestDto.toEntity());

        return id;
    }

    @Transactional
    public NovelMasterResponseDto findById(long id) {

        NovelMaster novelMaster = novelMasterRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("소설을 찾을 수 없습니다. {id}=" + id));

        return new NovelMasterResponseDto(novelMaster);
    }

    @Transactional
    public Long delete(long id) {

        NovelMaster novelMaster = novelMasterRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("소설을 찾을 수 없습니다. {id}=" + id));

        novelMasterRepository.delete(novelMaster);
        return id;
    }
}
