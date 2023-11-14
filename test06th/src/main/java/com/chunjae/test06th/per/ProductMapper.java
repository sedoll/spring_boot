package com.chunjae.test06th.per;

import com.chunjae.test06th.entity.Comment;
import com.chunjae.test06th.entity.FileDTO;
import com.chunjae.test06th.entity.FileVO;
import com.chunjae.test06th.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getProductList(); // 리스트 목록
    public Integer fileBoardInsert(Product fileboard) throws Exception; // 판매글 내용 db 저장
    Integer fileInsert(FileDTO file) throws Exception; // 판매글의 파일 db 저장
    int productUpdate(Product product) throws Exception; // 글 수정
    
    public Product latestFileboard() throws Exception;
    public List<FileDTO> getFileGroupList(int postNo) throws Exception;
    public Product getProduct(Integer postNo) throws Exception;

    int fileboardDelete(int no) throws Exception; // product 제거
    public int fileRemove(int no) throws Exception; // 하나의 파일 제거
    public FileDTO getFile(int no) throws Exception; // 하나의 파일 갖고오기
    public void fileboardUpdate(Product product) throws Exception; // 거래글 수정
    public void fileUpdate(FileDTO fileDTO) throws Exception; // 파일 수정
    public void removeFileAll(int postNo) throws Exception; // 해당 상품의 모든 파일 제거
}