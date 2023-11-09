package com.shop.biz;

import com.shop.entity.Item;
import com.shop.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.ImageConsumer;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Item findById(long itemId) throws Exception {
        return itemMapper.findById(itemId);
    }

    @Override
    public List<Item> findItemAll() throws Exception {
        return itemMapper.findItemAll();
    }

    @Override
    public int save(Item item) throws Exception {
        return itemMapper.save(item);
    }

    @Override
    public Item getLatestItem() throws Exception {
        return itemMapper.getLatestItem();
    }
}
