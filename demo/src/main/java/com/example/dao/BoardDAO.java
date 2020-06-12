package com.example.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.vo.BoardVO;

public interface BoardDAO {
	public int insertBoard(BoardVO obj);

	public BoardVO selectBoardOne(int no);

	public List<BoardVO> selectBoard(HashMap<String,Object> map);

	public int updateBoard(BoardVO obj);

	public int deleteBoard(BoardVO obj);
	
	public int countBoard(); //전체 카운트 구하기
	
	public int updateHit(int no );
	public int insertBatch(List<BoardVO> list); //Board.insertBatch
	public BoardVO selectBoardImg(int no);
}
