package com.nisum.assignment.services;

import com.nisum.assignment.dtos.*;
import com.nisum.assignment.entities.ItemEntity;
import com.nisum.assignment.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public CreateItemResponseDto createItem(CreateItemRequestDto item) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(item.name);
        itemEntity.setPrice(item.price);
        ItemEntity savedItem = itemRepository.save(itemEntity);
        return CreateItemResponseDto.builder()
                .id(savedItem.getId())
                .name(savedItem.getName())
                .price(savedItem.getPrice())
                .build();
    }

    public GetItemResponseDto getItem(UUID id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        return GetItemResponseDto.builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .price(itemEntity.getPrice())
                .build();
    }

    public ArrayList<GetItemResponseDto> getAllItems(GetItemsRequestParamsDto params) {
        ArrayList<ItemEntity> itemEntities = (ArrayList<ItemEntity>) itemRepository.findByOptionalFilters(
                params.getName().orElse(null),
                params.getMinPrice().orElse(null),
                params.getMaxPrice().orElse(null)
        );
        ArrayList<GetItemResponseDto> items = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities) {
            GetItemResponseDto item = GetItemResponseDto.builder()
                    .id(itemEntity.getId())
                    .name(itemEntity.getName())
                    .price(itemEntity.getPrice())
                    .build();
            items.add(item);
        }
        return items;
    }

    public GetItemResponseDto updateItem(UUID id, UpdateItemRequestDto item) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        if(item.name != null){
            itemEntity.setName(item.name);
        }
        if(item.price != null){
            itemEntity.setPrice(item.price);
        }

        ItemEntity updatedItem = itemRepository.save(itemEntity);
        return GetItemResponseDto.builder()
                .id(updatedItem.getId())
                .name(updatedItem.getName())
                .price(updatedItem.getPrice())
                .build();
    }

    public void deleteItem(UUID id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(itemEntity);
    }
}
