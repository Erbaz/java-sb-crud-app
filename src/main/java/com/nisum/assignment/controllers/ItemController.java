package com.nisum.assignment.controllers;

import com.nisum.assignment.dtos.*;
import com.nisum.assignment.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<CreateItemResponseDto> createItem(@Validated @RequestBody CreateItemRequestDto item) {
        CreateItemResponseDto response = itemService.createItem(item);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetItemResponseDto> getItem(@PathVariable UUID id) {
        GetItemResponseDto response = itemService.getItem(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ArrayList<GetItemResponseDto>> getAllItems(@RequestParam GetItemsRequestParamsDto params) {
        ArrayList<GetItemResponseDto> response = itemService.getAllItems(params);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetItemResponseDto> updateItem(@PathVariable UUID id, @Validated @RequestBody UpdateItemRequestDto item) {
        GetItemResponseDto response = itemService.updateItem(id, item);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

}
