package server.board.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import server.board.model.domain.Board;
import server.board.mybatis.comfig.MybatisManager;

public class BoardDAO {
	MybatisManager manager = MybatisManager.getInstance();
	
	//�۾���
	public int insert(Board board) {
		SqlSession sqlSession = manager.getSqlSession();
		int result = sqlSession.insert("Board.insert", board);
		sqlSession.commit();
		manager.release(sqlSession);
		return result;
	}
	
	//�Ѱ� ��ȸ
	public Board select(Board board) {
		SqlSession sqlSession = manager.getSqlSession();
		Board result =sqlSession.selectOne("Board.select",board);
		manager.release(sqlSession);
		return result;
		
	}
	
	//��ü ��ȸ
	public List selectAll() {
		SqlSession sqlSession = manager.getSqlSession();
		List list =sqlSession.selectList("Board.selectAll");
		manager.release(sqlSession);
		return list;
		
	}
}
