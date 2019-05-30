package server.board.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import server.board.model.domain.Board;
import server.board.mybatis.comfig.MybatisManager;

public class BoardDAO {
	MybatisManager manager = MybatisManager.getInstance();
	
	//글쓰기
	public int insert(Board board) {
		SqlSession sqlSession = manager.getSqlSession();
		int result = sqlSession.insert("Board.insert", board);
		sqlSession.commit();
		manager.release(sqlSession);
		return result;
	}
	
	//한건 조회
	public Board select(Board board) {
		SqlSession sqlSession = manager.getSqlSession();
		Board result =sqlSession.selectOne("Board.select",board);
		manager.release(sqlSession);
		return result;
		
	}
	
	//전체 조회
	public List selectAll() {
		SqlSession sqlSession = manager.getSqlSession();
		List list =sqlSession.selectList("Board.selectAll");
		manager.release(sqlSession);
		return list;
		
	}
}
