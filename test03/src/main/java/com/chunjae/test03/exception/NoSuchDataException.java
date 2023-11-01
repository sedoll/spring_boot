package com.chunjae.test03.exception;

// 데이터 검색 예외 처리
public class NoSuchDataException extends RuntimeException {
    /*
     * 만약 테이블이 비어있다면, 빈 리스트 반환하는데
     * 이러한 경우 npt(null이랑 비슷한거) 값을 비교하기 위해 사용
     * */
    public NoSuchDataException(String msg) {
        super(msg);
    }
}
