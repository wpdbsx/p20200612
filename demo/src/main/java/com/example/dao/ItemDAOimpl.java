package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vo.BoardVO;
import com.example.vo.ItemVO;

@Service
@Transactional
public class ItemDAOimpl implements ItemDAO {
	@Autowired // @Bean으로 만들어진 객체를 받아옴.
	private SqlSessionFactory sqlFactroy = null;

	@Override

	public List<ItemVO> selectItem(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectList("Item.selectList", map);
	}

	@Override
	public int countBoard() {
		return sqlFactroy.openSession().selectOne("Item.count");

	}

	@Override
	public ItemVO selectItemOnt(int no) {

		return sqlFactroy.openSession().selectOne("Item.selectOne", no);
	}

	@Override
	public List<ItemVO> selectItemSearch(String txt) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectList("Item.selectItemSearch", txt);
	}

	@Override
	public int insertItemBatch(List<ItemVO> list) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().insert("Item.insertItemBatch", list);
	}

	@Override
	public List<ItemVO> selectItemList() {
		// TODO Auto-generated method stub

		return sqlFactroy.openSession().selectList("Item.selectItemList1");
	}

	@Override
	public int deleteItemBatch(int[] no) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().delete("Item.deleteItemBatch",no);
	}

	@Override
	public List<ItemVO> selectItemWhere(int[] itemno) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().selectList("Item.selectItemWhere",itemno);
	}

	@Override
	public int updateItemBatch(List<ItemVO> list) {
		// TODO Auto-generated method stub
		return sqlFactroy.openSession().update("Item.updateItemBatch",list);
	}

	@Override
	public int deleteItemOne(int no) {
		return sqlFactroy.openSession().delete("Item.deleteItemOne", no);
	}

}
