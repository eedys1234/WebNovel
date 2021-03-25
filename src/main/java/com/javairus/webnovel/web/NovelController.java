package com.javairus.webnovel.web;

import com.javairus.webnovel.domain.enums.EnumMapper;
import com.javairus.webnovel.domain.novels.NovelMaster;
import com.javairus.webnovel.domain.novels.NovelValidator;
import com.javairus.webnovel.service.NovelDetailService;
import com.javairus.webnovel.service.NovelMasterService;
import com.javairus.webnovel.web.dto.novels.NovelDetailSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelDetailUpdateRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelMasterSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelMasterUpdateRequestDto;
import com.javairus.webnovel.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@ControllerAdvice
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/novels")
public class NovelController {

    private final NovelMasterService novelMasterService;

    private final NovelDetailService novelDetailService;

    private final NovelValidator novelValidator;

    private final EnumMapper enumMapper;

    @PostMapping("/master")
    public ResponseEntity<ApiResponse> masterSave(@RequestBody NovelMasterSaveRequestDto requestDto,
                                            UriComponentsBuilder uriComponentsBuilder,
                                            Errors errors) {

        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Long id = novelMasterService.save(requestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("{id}").buildAndExpand(id).toUri());

        return new ResponseEntity<>(ApiResponse.of(200, "", id),
                HttpStatus.CREATED);
    }

    @PutMapping("/master/{id}")
    public ResponseEntity<ApiResponse> masterUpdate(@PathVariable("id") Long id,
                                              @RequestBody NovelMasterUpdateRequestDto requestDto,
                                              Errors errors) {

        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(ApiResponse.of(200, "", novelMasterService.update(id, requestDto)),
                HttpStatus.OK);
    }

    @GetMapping("/master/{id}")
    public ResponseEntity<ApiResponse> MasterfindById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ApiResponse.of(200, "", novelMasterService.findById(id)),
                HttpStatus.OK);
    }

//    @GetMapping("/masters")

    @DeleteMapping("/master/{id}")
    public ResponseEntity<ApiResponse> masterDelete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ApiResponse.of(200, "", novelMasterService.delete(id)),
                HttpStatus.OK
        );
    }

    @PostMapping("/detail/{novelMasterId}")
    public ResponseEntity<ApiResponse> detailSave(@PathVariable("novelMasterId") Long novelMasterId,
                                            @RequestBody @Valid NovelDetailSaveRequestDto requestDto,
                                            UriComponentsBuilder uriComponentsBuilder,
                                            Errors errors) {

        //validation check
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        novelValidator.validate(requestDto, errors);

        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Long id = novelDetailService.save(novelMasterId, requestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(ApiResponse.of(200, "", id), httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping("/detail/{id}")
    public ResponseEntity<ApiResponse> detailUpdate(@PathVariable("id") Long id,
                                              @RequestBody @Valid NovelDetailUpdateRequestDto requestDto,
                                              Errors errors) {
        //validation check
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        novelValidator.validate(requestDto, errors);

        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(ApiResponse.of(200, "", novelDetailService.update(id, requestDto)),
                HttpStatus.OK);
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse> detailGets(@RequestParam("pageNo") Integer pageNo,
                                            @RequestParam("offset") Integer offset,
                                            @PathVariable("novelMasterId") Long novelMasterId){

        return new ResponseEntity<>(ApiResponse.of(200, "",
                novelDetailService.gets(novelMasterId, pageNo, offset)), HttpStatus.OK);
    }

    @DeleteMapping("/detail/{id}")
    public ResponseEntity<ApiResponse> detailDelete(@PathVariable("id") Long id) {

        return new ResponseEntity<>(ApiResponse.of(200, "",
                novelDetailService.delete(id)), HttpStatus.OK);
    }

    @GetMapping("/categorys")
    public ResponseEntity<ApiResponse> categoryGets() {
        String categorys = "categorys";
        return new ResponseEntity<>(ApiResponse.of(200, "", enumMapper.find(categorys)),
                HttpStatus.OK);
    }

    @GetMapping("/novelcodes")
    public ResponseEntity<ApiResponse> novelCodeGets() {
        String novelCodes = "novelCodes";
        return new ResponseEntity<>(ApiResponse.of(200, "", enumMapper.find(novelCodes)),
                HttpStatus.OK);
    }

    @GetMapping("/ages")
    public ResponseEntity<ApiResponse> ageGets() {
        String ages = "ages";
        return new ResponseEntity<ApiResponse>(ApiResponse.of(200, "", enumMapper.find(ages)),
                HttpStatus.OK);
    }
}
