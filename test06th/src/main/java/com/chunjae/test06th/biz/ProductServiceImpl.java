package com.chunjae.test06th.biz;

import com.chunjae.test06th.entity.Comment;
import com.chunjae.test06th.entity.FileDTO;
import com.chunjae.test06th.entity.FileVO;
import com.chunjae.test06th.entity.Product;
import com.chunjae.test06th.per.ProductMapper;
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

    // 게시글 상세 보기
    @Override
    public Product getProduct(Integer no) throws Exception {
        return productMapper.getProduct(no);
    }

    // 게시글 작성
    @Override
    public Integer insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }

    @Override
    public int updatProduct(Product product) {
        return productMapper.updatProduct(product);
    }

    @Override
    public int deleProduct(Integer no) {
        return productMapper.deleProduct(no);
    }

    @Override
    public List<Comment> CommentList(Integer par) {
        return productMapper.commentList(par);
    }

    @Override
    public int inserProductCom(Comment comment) {
        return productMapper.inserProductCom(comment);
    }
    // endregion


    // region 파일 관련
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
    public List<FileVO> getFileList() throws Exception {
        return productMapper.getFileList();
    }

    @Override
    public List<FileDTO> getFileGroupList(int postNo) throws Exception {
        return productMapper.getFileGroupList(postNo);
    }

    @Override
    public FileVO getFileObject(int no) throws Exception {
        return null;
    }

    @Override
    public void removeFileboard(int postNo) throws Exception {
        productMapper.removeFileboard(postNo);
    }

    @Override
    public void fileRemove(int no) throws Exception {
        productMapper.fileRemove(no);
    }

    @Override
    public FileDTO getFile(int no) throws Exception {
        return productMapper.getFile(no);
    }

    @Override
    public void updateFileboard(FileVO fileboard) throws Exception {
        productMapper.updateFileboard(fileboard);
    }
    @Override
    public void removeFileAll(int postNo) throws Exception {
        productMapper.removeFileAll(postNo);
    }
    // endregion
}
