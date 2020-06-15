package com.example.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.BoardVO;
import com.example.vo.MemberVO;

@Service
@Transactional
public class MemberDAO {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private SqlSessionFactory sqlFactroy = null;

	public int insertMember(MemberVO obj) {

		return sqlFactroy.openSession().insert("Member.join", obj);
	}

	public List<MemberVO> selectMemberList() {
		return sqlFactroy.openSession().selectList("Member.memberList");
	}
	public List<MemberVO> selectMemberID(String[] id) {
		return sqlFactroy.openSession().selectList("Member.memberListid",id);
	}
	public MemberVO selectMemberLogin(MemberVO obj) {
		return sqlFactroy.openSession().selectOne("Member.login", obj);
	}
	public int deleteMemberList(String[] obj) {
		return sqlFactroy.openSession().delete("Member.deleteList", obj);
	}
	public int updateMeberList(List<MemberVO> obj) {
		return sqlFactroy.openSession().update("Member.updateList",obj);
	}
}
