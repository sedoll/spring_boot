package com.chunjae.test06th.biz;

import com.chunjae.test06th.entity.Comment;
import com.chunjae.test06th.entity.FileDTO;
import com.chunjae.test06th.entity.FileVO;
import com.chunjae.test06th.entity.Product;
import com.chunjae.test06th.per.ProductMapper;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    // region 게시판 관련
    @Autowired
    private ProductMapper productMapper;

    // 게시글 목록 보기
    @Override
    public List<Product> productList() {
        return productMapper.getProductList();
    }

    @Override
    public List<Product> myProductList(String id) {
        return productMapper.getMyProductList(id);
    }

    // 글 상세 보기
    @Override
    public Product getProduct(Integer no) throws Exception {
        return productMapper.getProduct(no);
    }

    // 글 수정
    @Override
    public int productUpdate(Product product) throws Exception {
        return productMapper.productUpdate(product);
    }

    // 조회수 + 1
    @Override
    public void countUp(int no) throws Exception {
        productMapper.countUp(no);
    }

    @Override
    public int actUpdate(Product product) {
        return productMapper.actUpdate(product);
    }

    // region 파일 관련
    // 중고게시글 및 묶여있는 파일 삭제
    @Override
    public int removeFileboard(int postNo) throws Exception {
        int ck = productMapper.fileboardDelete(postNo);
        productMapper.removeFileAll(postNo);
        return ck;
    }

    @Override
    public void insertFileboard(FileVO fileboard) throws Exception {
        Product product = fileboard.getFileBoard();
        List<FileDTO> fileList = fileboard.getFileList();
        productMapper.fileBoardInsert(product);
        Product latestBoard = productMapper.latestFileboard();
        for(FileDTO file:fileList) {
            file.setPno(latestBoard.getNo());
            productMapper.fileInsert(file);
        }
    }

    @Override
    public List<FileDTO> getFileGroupList(int postNo) throws Exception {
        return productMapper.getFileGroupList(postNo);
    }

    @Override
    public void updateFileboard(FileVO fileboard) throws Exception {
        Product board = fileboard.getFileBoard();
        List<FileDTO> fileList = fileboard.getFileList();
        productMapper.fileboardUpdate(board);
        for(FileDTO file:fileList) {
            productMapper.fileUpdate(file);
        }
    }
    @Override
    public void removeFileAll(int postNo) throws Exception {
        productMapper.removeFileAll(postNo);
    }

    @Override
    public FileDTO thmbn(int no) throws Exception {
        return productMapper.thmbn(no);
    }

    // endregion
}
