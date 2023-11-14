package com.chunjae.test06th.biz;

import com.chunjae.test06th.entity.Comment;
import com.chunjae.test06th.entity.FileDTO;
import com.chunjae.test06th.entity.FileVO;
import com.chunjae.test06th.entity.Product;

import java.util.List;


public interface ProductService {
    public List<Product> productList(); // 글 목록 보기
    public List<Product> myProductList(String id); // 내 상점 조회
    public Product getProduct(Integer no) throws Exception; // 글 상세 보기
    public int productUpdate(Product product) throws Exception; // 글 수정
    public void insertFileboard(FileVO fileboard) throws Exception; // 글 추가
    public List<FileDTO> getFileGroupList(int postNo) throws Exception; // 글에 들어간 파일 목록 보기
    public int removeFileboard(int postNo) throws Exception; // product, file 삭제
    public void updateFileboard(FileVO fileboard) throws Exception; // 파일, 글 수정
    public void removeFileAll(int postNo) throws Exception; // 글에 들어간 파일 전체 삭제
    public void countUp(int no) throws Exception; // 중고 거래 게시글 조회수 +1
    public FileDTO thmbn(int no) throws Exception; // 썸네일 이미지 가져오기
    public int actUpdate(Product product); // 상품 거래 완료 처리
}
