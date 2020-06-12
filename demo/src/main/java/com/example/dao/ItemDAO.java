package com.example.dao;

import java.util.HashMap;
import java.util.List;

import com.example.vo.BoardVO;
import com.example.vo.ItemVO;

public interface ItemDAO {
	public List<ItemVO> selectItem(HashMap<String, Object> map);
	
	public int countBoard();
	public ItemVO selectItemOnt(int no);
	public List<ItemVO> selectItemList();
	public List<ItemVO> selectItemSearch(String txt); //Item.selectItemSearch
	public int insertItemBatch(List<ItemVO> list);
	public int deleteItemBatch(int[] no);
	public List<ItemVO> selectItemWhere(int[] itemno);
	public int updateItemBatch(List<ItemVO> list);
}
