package com.fileupload.controller;

import com.fileupload.dto.FileDto;
import com.fileupload.dto.ItemDto;
import com.fileupload.request.FileRequest;
import com.fileupload.request.ItemRequest;
import com.fileupload.service.FileService;
import com.fileupload.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FileUploadController {
    private final ItemService itemService;
    private final FileService fileService;

    @Value("${image.path}")
    private String uploadDir;

    @GetMapping("/")
    public String homeView(Model model) {
        model.addAttribute("item", new ItemRequest());
        return "index";
    }

    @PostMapping("/form")
    public String saveFormRequests(
            @Valid @ModelAttribute("item") ItemRequest itemRequest,
            BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            log.info("BindingResult = {}", bindingResult.getFieldError());
            return "index";
        }
        String itemName = itemRequest.getItemName();
        Integer qty = itemRequest.getQty();
        ItemDto itemDto = ItemDto.builder().itemName(itemName).qty(qty).build();

        if (itemRequest.getFile() != null) {
            MultipartFile file = itemRequest.getFile();
            String fullPath = uploadDir + file.getOriginalFilename();
            File Folder = new File(uploadDir);
            if(!Folder.exists()){
                try {
                    Folder.mkdir();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            file.transferTo(new File(fullPath));
            log.info("file.getOriginalFilename = {}", file.getOriginalFilename());
            log.info("fullPath = {}", fullPath);

            FileDto fileDto = FileDto.builder()
                    .originFileName(file.getOriginalFilename())
                    .fullPath(uploadDir + file.getOriginalFilename())
                    .build();
            Long savedFileId = fileService.save(fileDto);
            itemDto.setFileId(savedFileId);
        }
        itemService.save(itemDto);
        return "redirect:/";
    }

    @PostMapping("/form-log")
    public String saveFormRequestsLog(
            @ModelAttribute("item") ItemRequest itemRequest,
            @ModelAttribute("file") FileRequest fileRequest) {
        log.info("itemRequest.getItemName = {}", itemRequest.getItemName());
        if (!fileRequest.getFile().isEmpty()) {
            log.info("fileRequest.getFile = {}", fileRequest.getFile().getOriginalFilename());
        }
        return "index";
    }
}
