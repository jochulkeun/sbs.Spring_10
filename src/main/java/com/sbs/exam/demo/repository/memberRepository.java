package com.sbs.exam.demo.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sbs.exam.demo.vo.Member;

@Mapper
public interface memberRepository {
	@Insert("INSERT INTO `member` (loginId,loginPw,`name`,nickname,cellPhoneNo,email,regDate,updateDate) "
			+ "VALUES(#{loginId},#{loginPw},#{name},#{nickname},#{cellPhoneNo},#{email},NOW(),NOW())")
	public void doJoin(String loginId, String loginPw, String name, String nickname, int cellPhoneNo, String email);
	@Select("SELECT * FROM `member` WHERE Id = #{Id}")
	public Member showMember(int id);

}
