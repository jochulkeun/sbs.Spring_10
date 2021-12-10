package com.sbs.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sbs.exam.demo.vo.Article;
import com.sbs.exam.demo.vo.ResultData;

@Mapper
public interface ArticleRepository {

	public void writeArticle(@Param("boardId") int boardId, @Param("memberId") int memberId,
			@Param("title") String title, @Param("body") String body);

	public Article getForPrintArticle(@Param("id") int id);

	public void deleteArticle(@Param("id") int id);

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	public List<Article> getForPrintArticles(@Param("boardId") int boardId, String searchKeywordTypeCode,
			String searchKeyword, int limitStart, int limitTake);

	public int getLastInsertId();

	public int articlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword);

	@Update("""
			<script>
			UPDATE article
			SET hitCount = hitCount + 1
			WHERE id = #{id}
			</script>
					""")
	public int increaseHitCount(int id);

	@Select("""
			<script>
			SELECT hitCount
			FROM article
			WHERE id = #{id}
			</script>
					""")
	public int getArticleHitCount(int id);

}