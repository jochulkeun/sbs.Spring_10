package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sbs.exam.demo.vo.Board;

@Mapper
public interface BoardRepository {
	@Select("""
			SELECT * FROM 
			board AS B 
			WHERE B.id = #{id} 
			AND delStatus=0
			""")
	Board getBoardIdById(@Param("id") int id);

}
