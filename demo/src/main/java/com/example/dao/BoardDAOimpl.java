package com.example.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.BoardVO;

@Service
@Transactional
public class BoardDAOimpl implements BoardDAO {
	
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private SqlSessionFactory sqlFactroy = null;
	
	@Override
	public int insertBoard(BoardVO obj) {
		// TODO Auto-generated method stub
		System.out.println("안녕");
		return sqlFactroy.openSession().insert("Board.insertBoard", obj);
	}

	@Override
	public BoardVO selectBoardOne(int no) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectOne("Board.selectBoardOne",no);
	}

	@Override
	public List<BoardVO> selectBoard(HashMap<String, Object> map) {
		
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectList("Board.selectList",map);
	}

	@Override
	public int updateBoard(BoardVO obj) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().update("Board.update",obj);
	}

	@Override
	public int deleteBoard(int no) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().delete("Board.delete",no);
	}
	@Override
	public int countBoard(String text) {
		return sqlFactroy.openSession().selectOne("Board.count",text);
		
		
	}
	@Override
	public int updateHit(int no ) {
		return sqlFactroy.openSession().update("Board.updateHit",no);
	}

	@Override
	public int insertBatch(List<BoardVO> list) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().insert("Board.insertBatch",list);
	}

	@Override
	public BoardVO selectBoardImg(int no) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectOne("Board.selectBoardImg",no);
	}

	@Override
	public int selectBoardPrev(int no) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectOne("Board.selectPrev",no);
	}

	@Override
	public int selectBoardNext(int no) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectOne("Board.selectNext",no);
	}
	
}
