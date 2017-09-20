package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

  private static SqlSessionFactory sqlSessionFactory = null;

  // 通过静态单例保证SqlSessionFactory只被实例化一次
  // SqlSessionFactoryBuilder的作用域是方法范围，创建了SqlSessionFactory后就没作用了
  // SqlSessionFactory的生命周期是整个应用执行期间，一直存在而不需要重复创建
  static {
    try {
      String resource = "mybatis.xml";
      //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
      InputStream inputStream = Resources.getResourceAsStream(resource);
      //构建sqlSession的工厂
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      sqlSessionFactory = builder.build(inputStream);
    }catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static SqlSession getSqlSession() {

    SqlSession sqlSession = sqlSessionFactory.openSession();

    return sqlSession;
  }
}
