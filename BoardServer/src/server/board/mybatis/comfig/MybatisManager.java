package server.board.mybatis.comfig;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private static MybatisManager instance;
	private MybatisManager() {
		String resource = "server/board/mybatis/comfig/mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	public static MybatisManager getInstance() {
		return instance = new MybatisManager();
	}
	
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	public void release(SqlSession sqlSession) {
		sqlSession.close();
	}
}
