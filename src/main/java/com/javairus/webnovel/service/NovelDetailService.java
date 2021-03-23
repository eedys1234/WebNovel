package com.javairus.webnovel.service;

import com.javairus.webnovel.domain.novels.NovelDetail;
import com.javairus.webnovel.domain.novels.NovelDetailRepository;
import com.javairus.webnovel.domain.novels.NovelMaster;
import com.javairus.webnovel.domain.novels.NovelMasterRepository;
import com.javairus.webnovel.web.dto.novels.NovelDetailResponseDto;
import com.javairus.webnovel.web.dto.novels.NovelDetailSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelDetailUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NovelDetailService {

    private final NovelMasterRepository novelMasterRepository;
    private final NovelDetailRepository novelDetailRepository;

    @Transactional
    public Long save(Long novelMasterId, NovelDetailSaveRequestDto requestDto) {

        NovelMaster novelMaster = novelMasterRepository.findById(novelMasterId)
                .orElseThrow(()-> new IllegalArgumentException("소설을 찾을 수 없습니다. {id}" + novelMasterId));

        NovelDetail novelDetail = requestDto.toEntity();
        novelDetail.setNovelMaster(novelMaster);

        return novelDetailRepository.save(novelDetail).getNovelDetailKey();
    }

    @Transactional
    public Long update(Long id, NovelDetailUpdateRequestDto requestDto) {

        NovelDetail novelDetail = novelDetailRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("소설을 찾을 수 없습니다. {id}" + id));

        novelDetail.update(requestDto.toEntity());

        return id;
    }

    @Transactional
    public List<NovelDetailResponseDto> gets(Long novelMasterId, Integer pageNo, Integer offset) {

        NovelMaster novelMaster = novelMasterRepository.findById(novelMasterId)
                .orElseThrow((()-> new IllegalArgumentException("소설을 찾을 수 없습니다. {id}" + novelMasterId)));

        Pageable pageable = PageRequest.of(pageNo-1, offset, Sort.by("regDate").descending());

        return novelDetailRepository.findAllByRegDate(pageable).stream()
                .map(NovelDetailResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long delete(Long id) {

        NovelDetail novelDetail = novelDetailRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("소설을 찾을 수 없습니다. {id}" + id));

        novelDetailRepository.delete(novelDetail);
        return id;
    }
}
