package com.chunjae.jpa1.per;

import com.chunjae.jpa1.entity.Board;
import javassist.compiler.ast.Keyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> searchOne(Pageable pageable);
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
