package com.chunjae.test06.biz;

import com.chunjae.test06.entity.FileDTO;
import com.chunjae.test06.entity.FileVO;
import com.chunjae.test06.entity.Product;
import com.chunjae.test06.entity.Comment;

import java.util.List;


public interface ProductService {

    // 게시글 목록 보기
    public List<Product> productList();

    // 게시글 상세 보기
    public Product getProduct(Integer no);
    
    // 게시글 작성
    public Integer insertProduct(Product product);
    public int updatProduct(Product product); // 수정
    public int deleProduct(Integer no); // 삭제

    public List<Comment> CommentList(Integer par); // 댓글 리스트 목록
    public int inserProductCom(Comment comment); // 댓글 입력

    public void insertFileboard(FileVO fileboard) throws Exception;
    public List<FileVO> getFileList() throws Exception;
    public List<FileDTO> getFileGroupList(int postNo) throws Exception;
    public FileVO getFilebord(int postNo) throws Exception;
    public FileVO getFileObject(int no) throws Exception;
    public void removeFileboard(int postNo) throws Exception;
    public void fileRemove(int no) throws Exception;
    public FileDTO getFile(int no) throws Exception;
    public void updateFileboard(FileVO fileboard) throws Exception;
    public void removeFileAll(int postNo) throws Exception;
}
