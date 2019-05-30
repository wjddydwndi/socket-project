package server.board.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import server.board.model.domain.B_Comment;
import server.board.model.domain.Board;
import server.board.mybatis.comfig.MybatisManager;

public class B_CommentDAO {
	MybatisManager manager = MybatisManager.getInstance();
	
	//댓글 한건 등록
	public int insert(B_Comment b_Comment) {
		SqlSession sqlSession = manager.getSqlSession();
		int result = sqlSession.insert("B_Comment.insert", b_Comment);
		sqlSession.commit();
		manager.release(sqlSession);
		return result;
	}
	
	//전체 댓글 조회
	public List selectAll(B_Comment b_Comment) {
		SqlSession sqlSession = manager.getSqlSession();
		List result = sqlSession.selectList("B_Comment.selectAll", b_Comment);
	
		manager.release(sqlSession);
		return result;
	}
	
	
}
