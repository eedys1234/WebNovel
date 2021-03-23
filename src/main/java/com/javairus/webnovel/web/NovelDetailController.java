package com.javairus.webnovel.web;

import com.javairus.webnovel.service.NovelDetailService;
import com.javairus.webnovel.web.dto.novels.NovelDetailSaveRequestDto;
import com.javairus.webnovel.web.dto.novels.NovelDetailUpdateRequestDto;
import com.javairus.webnovel.web.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.ws.Response;

@ControllerAdvice
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/novels")
public class NovelDetailController {

    private final NovelDetailService novelDetailService;

    @PostMapping("/detail")
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody NovelDetailSaveRequestDto requestDto,
                                            UriComponentsBuilder uriComponentsBuilder,
                                            Errors errors) {

        //validation check
        if(errors.hasErrors()) {

        }

        Long id = novelDetailService.save(requestDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(ApiResponse.of(200, "", id), httpHeaders, HttpStatus.CREATED);

    }

    @PutMapping("/detail/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                              @RequestBody NovelDetailUpdateRequestDto requestDto) {

        return new ResponseEntity<>(ApiResponse.of(200, "", novelDetailService.update(id, requestDto)),
                HttpStatus.OK);
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse> gets(@RequestParam("pageNo") Integer pageNo,
                                            @RequestParam("offset") Integer offset,
                                            @PathVariable("novelMasterId") Long novelMasterId){

        return new ResponseEntity<>(ApiResponse.of(200, "",
                novelDetailService.gets(novelMasterId, pageNo, offset)), HttpStatus.OK);
    }

    @DeleteMapping("/detail/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {

        return new ResponseEntity<>(ApiResponse.of(200, "",
                novelDetailService.delete(id)), HttpStatus.OK);
    }
}
