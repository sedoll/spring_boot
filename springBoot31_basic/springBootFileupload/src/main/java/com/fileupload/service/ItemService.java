package com.fileupload.service;

import com.fileupload.dto.ItemDto;
import com.fileupload.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Long save(ItemDto itemDto) {
        return itemRepository.save(itemDto.toEntity()).getId();
    }
}
