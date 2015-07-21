package board.model;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestDAO {
	
	private static final String NAME_SPACE = "board.model.ArticleMapper";
	
	private SqlSessionFactory getSessionFactory(){
		
	   String resource = "mybatis-config.xml";
	   InputStream input = null;
	   try{
		   input=Resources.getResourceAsStream(resource);
	   }
	   
	   catch(Exception ex){
	   }
	   
    return new SqlSessionFactoryBuilder().build(input);
    
	}
	
	
	public Article read(int Article_id) {
		
		Article article = null;
		
		SqlSession session = getSessionFactory().openSession();
		
		try {
			article = (Article) session.selectOne(NAME_SPACE + ".selectById", Article_id);
		}
		
		catch (Exception ex) {
		} 
		
		finally {
			session.close();
		}
		
		return article;
	}
	
	public ArrayList<Article> getList(ArticleListModel listmodel){
		ArrayList<Article> list = null;
		
		SqlSession session = getSessionFactory().openSession();
		
		try {
			
		}
		
		catch (Exception ex) {
		} 
		
		finally {
			session.close();
		}
		
		return list;
	}
	
}
