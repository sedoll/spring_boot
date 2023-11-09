package com.shop.biz;

import com.shop.entity.Item;

import java.util.List;

public interface ItemService {
    public Item findById(long itemId) throws Exception;
    public List<Item> findItemAll() throws Exception;
    public int save(Item item) throws Exception;
    public Item getLatestItem() throws Exception;
}
