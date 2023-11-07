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
    Product getProduct(Integer id); // 상세
    int insertProduct(Product product); // 추가
    int updatProduct(Product product); // 수정
    int deleProduct(Integer id); // 삭제
    List<Comment> commentList(Integer par); // 댓글 리스트 목록
    int inserProductCom(Comment comment); // 댓글 입력


    public Integer fileBoardInsert(Product fileboard) throws Exception;
    public Product latestFileboard() throws Exception;
    public Integer fileInsert(FileDTO file) throws Exception;
    public List<FileVO> getFileList() throws Exception; // 리스트 목록
    public List<FileDTO> getFileGroupList(int postNo) throws Exception;
    public FileVO getFilebord(int postNo) throws Exception;
    public FileVO getFileObject(int no) throws Exception;
    public void removeFileboard(int postNo) throws Exception;
    public void fileRemove(int no) throws Exception;
    public FileDTO getFile(int no) throws Exception;
    public void updateFileboard(FileVO fileboard) throws Exception;
    public void removeFileAll(int postNo) throws Exception;
}