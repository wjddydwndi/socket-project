package server.board.model.repository;

import org.apache.ibatis.session.SqlSession;

import server.board.model.domain.Member;
import server.board.mybatis.comfig.MybatisManager;

public class MemberDAO {
	MybatisManager manager = MybatisManager.getInstance();
	
	public Member checkId(Member member) {
		SqlSession sqlSession = manager.getSqlSession();
		Member result=sqlSession.selectOne("Member.checkId", member);
		manager.release(sqlSession);
		return result;
	}
	
	//회원가입
	public int insert(Member member) {
		SqlSession sqlSession = manager.getSqlSession();
		int result=sqlSession.insert("Member.insert",member);
		sqlSession.commit();
		manager.release(sqlSession);
		return result;
	}
	
	//로그인
	public Member login(Member member) {
		System.out.println("MemberDAO =="+member.getId()+"/"+member.getPass());
		
		SqlSession sqlSession = manager.getSqlSession();
		Member result=  sqlSession.selectOne("Member.login", member);
		manager.release(sqlSession);
		return result;
	}
	
	public Member select(Member member) {
		SqlSession sqlSession = manager.getSqlSession();
		Member result = sqlSession.selectOne("Member.select", member);
		manager.release(sqlSession);
		return result;
	}
}
