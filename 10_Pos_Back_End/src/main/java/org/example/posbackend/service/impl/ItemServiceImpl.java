package org.example.posbackend.service.impl;

import org.example.posbackend.dto.ItemDto;
import org.example.posbackend.entity.Item;
import org.example.posbackend.repository.ItemRepository;
import org.example.posbackend.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.posbackend.exceptions.CustomException;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDto itemDto) {

        if (itemDto == null) {
            throw new CustomException("Item data cannot be null");
        }

        if (itemRepository.existsById(itemDto.getId())) {
            throw new CustomException("Item with id " + itemDto.getId() + " already exists");
        }

        itemRepository.save(modelMapper.map(itemDto, Item.class));
    }

    @Override
    public void updateItem(ItemDto itemDto) {

        if (itemDto == null) {
            throw new CustomException("Item data cannot be null");
        }

        if (!itemRepository.existsById(itemDto.getId())) {
            throw new CustomException("Item with id " + itemDto.getId() + " does not exist");
        }

        itemRepository.save(modelMapper.map(itemDto, Item.class));
    }

    @Override
    public void deleteItem(String id) {

        if (!itemRepository.existsById(id)) {
            throw new CustomException("Item with id " + id + " does not exist");
        }

        itemRepository.deleteById(id);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .toList();
    }
}
