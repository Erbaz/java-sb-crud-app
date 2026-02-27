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
        return CreateItemResponseDto.builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .price(itemEntity.getPrice())
                .build();
    }

    public ItemDto getItem(UUID id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        return ItemDto.builder()
                .id(itemEntity.getId())
                .name(itemEntity.getName())
                .price(itemEntity.getPrice())
                .build();
    }

    public ArrayList<ItemDto> getAllItems(GetItemsRequestParamsDto params) {
        ArrayList<ItemEntity> itemEntities = (ArrayList<ItemEntity>) itemRepository.findByOptionalFilters(
                params.getName().orElse(null),
                params.getMinPrice().orElse(null),
                params.getMaxPrice().orElse(null)
        );
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities) {
            ItemDto itemDto = ItemDto.builder()
                    .id(itemEntity.getId())
                    .name(itemEntity.getName())
                    .price(itemEntity.getPrice())
                    .build();
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    public ItemDto updateItem(UUID id, UpdateItemRequestDto item) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        if(item.name != null){
            itemEntity.setName(item.name);
        }
        if(item.price != null){
            itemEntity.setPrice(item.price);
        }

        ItemEntity updatedItem = itemRepository.save(itemEntity);
        return ItemDto.builder()
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
