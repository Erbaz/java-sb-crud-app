package com.nisum.assignment.controllers;

import com.nisum.assignment.dtos.CreateItemRequestDto;
import com.nisum.assignment.dtos.CreateItemResponseDto;
import com.nisum.assignment.dtos.GetItemsRequestParamsDto;
import com.nisum.assignment.dtos.ItemDto;
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
    public ResponseEntity<ItemDto> getItem(@PathVariable UUID id) {
        ItemDto response = itemService.getItem(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ArrayList<ItemDto>> getAllItems(@RequestParam GetItemsRequestParamsDto params) {
        ArrayList<ItemDto> response = itemService.getAllItems(params);
        return ResponseEntity.ok(response);
    }

}
