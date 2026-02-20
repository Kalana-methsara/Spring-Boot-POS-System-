package org.example.posbackend.controller;

import jakarta.validation.Valid;
import org.example.posbackend.dto.ItemDto;
import org.example.posbackend.service.ItemService;
import org.example.posbackend.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> saveItem(@RequestBody @Valid ItemDto itemDto) {

        itemService.saveItem(itemDto);

        return new ResponseEntity<>(
                new APIResponse<>(
                        201,
                        "Item Created Successfully",
                        null
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateItem(@RequestBody @Valid ItemDto itemDto) {

        itemService.updateItem(itemDto);

        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Item Updated Successfully",
                        null
                )
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteItem(@PathVariable @Valid String id) {

        itemService.deleteItem(id);

        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Item Deleted Successfully",
                        null
                )
        );
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ItemDto>>> getAllItems(){

        List<ItemDto> itemList = itemService.getAllItems();

        return ResponseEntity.ok(
                new APIResponse<>(
                        200,
                        "Item List Fetched Successfully",
                        itemList
                )
        );
    }
}
