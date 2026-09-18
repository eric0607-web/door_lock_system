package com.doorlock.api.controller;

import com.doorlock.api.dto.StoreCreateRequest;
import com.doorlock.api.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private  final StoreService storeService;

    @PostMapping
    public ResponseEntity<Long> createStore(@RequestBody StoreCreateRequest request){
        Long storeId = storeService.createStore(request);
        return ResponseEntity.ok(storeId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
