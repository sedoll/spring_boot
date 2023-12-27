package com.fileupload.service;

import com.fileupload.dto.FileDto;
import com.fileupload.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    @Transactional
    public Long save(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

}
