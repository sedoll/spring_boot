package com.shop.mapper;

import com.shop.entity.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    Item findById(long itemId) throws Exception;
    List<Item> findItemAll() throws Exception;
    int save(Item item) throws Exception;
    Item getLatestItem() throws Exception;
}
