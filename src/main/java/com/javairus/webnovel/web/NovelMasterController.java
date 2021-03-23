package com.javairus.webnovel.web;

import com.javairus.webnovel.service.NovelMasterService;
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

@ControllerAdvice
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/novels")
public class NovelMasterController {

    private final NovelMasterService novelMasterService;

    @PostMapping("/master")
    public ResponseEntity<ApiResponse> save(@RequestBody NovelMasterSaveRequestDto requestDto,
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
    public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
                                              @RequestBody NovelMasterUpdateRequestDto requestDto,
                                              Errors errors) {

        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(ApiResponse.of(200, "", novelMasterService.update(id, requestDto)),
                HttpStatus.OK);
    }

    @GetMapping("/master/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ApiResponse.of(200, "", novelMasterService.findById(id)),
                HttpStatus.OK);
    }

//    @GetMapping("/masters")

    @DeleteMapping("/master/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ApiResponse.of(200, "", novelMasterService.delete(id)),
                HttpStatus.OK
        );
    }
}
