package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sbs.exam.demo.vo.Member;

@Mapper
public interface memberRepository {
	@Insert("INSERT INTO `member` (loginId,loginPw,`name`,nickname,cellphoneNo,email,regDate,updateDate) "
			+ "VALUES(#{loginId},#{loginPw},#{name},#{nickname},#{cellphoneNo},#{email},NOW(),NOW())")
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email);
	@Select("SELECT LAST_INSERT_ID()")
	int LastInsertId();
	@Select("SELECT * FROM `member` WHERE id = #{id}")
	public Member getMember(int id);
	@Select("SELECT * FROM `member` WHERE loginId = #{loginId}")
	public Member getMemberloginId(String loginId);
	@Select("SELECT * FROM `member` WHERE name = #{name} AND email = #{email}")
	public Member getMemberloginIdANDemail(@Param("name") String name,@Param("email") String email);
	
	
	
	

}
