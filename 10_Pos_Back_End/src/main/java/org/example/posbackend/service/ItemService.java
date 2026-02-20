package org.example.posbackend.service;

import org.example.posbackend.dto.ItemDto;

import java.util.List;

public interface ItemService {

    void saveItem(ItemDto itemDto);

    void updateItem(ItemDto itemDto);

    void deleteItem(String id);

    List<ItemDto> getAllItems();
}
