package com.shop.ctrl;

import com.shop.biz.ItemServiceImpl;
import com.shop.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/item/*")
public class ItemController {

    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping("items")
    public String getItemAll(Model model) throws Exception {
        List<Item> items = itemService.findItemAll();
        model.addAttribute("items", items);
        return "item/items";
    }

    @GetMapping("{itemId}")
    public String getItem(@PathVariable long itemId, Model model) throws Exception {
        Item item = itemService.findById(itemId);
        model.addAttribute("item", item);
        return "item/item";
    }

    @GetMapping("add")
    public String addForm() throws Exception {
        return "item/addItem";
    }

    @PostMapping("add")
    public String addItem(Item item, Model model) throws Exception{
        int ck = itemService.save(item);
        return "redirect:/item/items";
    }

    @GetMapping("latestItem")
    public String getLatestItem(Model model) throws Exception {
        Item item = itemService.getLatestItem();
        model.addAttribute("item", item);
        return "item/item";
    }

}
