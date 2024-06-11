package com.example.order.cafe.controller;

import com.example.order.cafe.domain.Cafe;
import com.example.order.cafe.domain.CafeMenu;
import com.example.order.cafe.dto.request.CafeCreateRequest;
import com.example.order.cafe.dto.request.CafeUpdateRequest;
import com.example.order.cafe.dto.response.CafeResponse;
import com.example.order.cafe.service.CafeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/cafes")
@Tag( name = "Cafe", description = "카페 API")
@RestController
public class CafeController {

    private final CafeService cafeService;

    @PostMapping
    @Operation(summary = "카페 등록", description = "새로운 카페의 프로필, 운영시간 정보를 등록한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "카페 정보 등록 성공"),
                    @ApiResponse(responseCode = "404", description = "카페 정보 등록 실패")
            })
    public ResponseEntity<CafeMenu> registerCafeMenu(@Validated @RequestBody CafeCreateRequest cafeCreateRequest){
        Cafe cafe = cafeService.registerCafe(cafeCreateRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("")
                .buildAndExpand(cafe.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{cafeId}")
    @Operation(summary = "카페 정보 수정", description = "카페 정보를 수정한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "카페 정보 수정 성공"),
                    @ApiResponse(responseCode = "404", description = "카페 정보 수정 실패")
            })
    public ResponseEntity<Object> updateCafe(@PathVariable("cafeId") long cafeId, @Validated @RequestBody CafeUpdateRequest cafeUpdateRequest){

        cafeService.updateCafe(cafeId, cafeUpdateRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cafeId}")
    @Operation(summary = "카페 정보 삭제", description = "카페의 정보를 삭제한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "카페 정보 삭제 성공"),
                    @ApiResponse(responseCode = "404", description = "카페 정보 삭제 실패")
            })
    public ResponseEntity<Object> deleteCafe(@PathVariable("cafeId") long cafeId){
        cafeService.deleteCafe(cafeId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cafeId}")
    @Operation(summary = "카페 정보 조회", description = "카페의 정보를 조회한다.",
        responses = {
                @ApiResponse(responseCode = "200", description = "카페 정보 조회 성공"),
                @ApiResponse(responseCode = "404", description = "카페 정보 조회 실패")
        })
    public ResponseEntity<CafeResponse> getCafe(@PathVariable("cafeId") long cafeId){

        return ResponseEntity.ok()
                .body(cafeService.getCafeById(cafeId));
    }

    @GetMapping("/all")
    @Operation(summary = "카페 정보 전체 조회", description = "모든 카페의 정보를 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "카페 정보 전체 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "카페 정보 전체 조회 실패")
            })
    public ResponseEntity<List<CafeResponse>> getAllCafe(){

        return ResponseEntity.ok()
                .body(cafeService.getAllCafe());
    }

}
