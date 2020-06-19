package com.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;



public interface BoardMapper {
		
	@Delete("DELETE FROM BOARD WHERE BRD_NO =#{no}")
	public int deleteBoard(@Param("no") int no);
}
		
