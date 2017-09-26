package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import api.mapper.TestMapper;
import api.entity.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class TestMybatis {
  @Test
  public void test() throws IOException {
    String resource = "mybatis.xml";
    //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
    InputStream inputStream = Resources.getResourceAsStream(resource);
    //构建sqlSession的工厂
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sessionFactory = builder.build(inputStream);

    SqlSession session = sessionFactory.openSession();
    /**
     * 映射sql的标识字符串，
     *
     mapper.loginMapper是loginMapper.xml文件中mapper标签的namespace属性的
     值，
     *
     getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
     */
    String statement1 = "mapper.TestMapper.getUser";//映射sql的标识字符串
    String statement2 = "mapper.TestMapper.getUserList";//映射sql的标识字符串
    //执行查询返回一个唯一user对象的sql
    Customer customer = new Customer();
    customer.setId(2);
    // 获取一个实体对象
//    Customer user = session.selectOne(statement1, customer);
    TestMapper testMapper = session.getMapper(TestMapper.class);
    Customer user = testMapper.getUser(customer);
    System.out.println("name: " + user.getName() + ";age: " + user.getAge() + ";sex: " + user.getSex() + "; id: " + user.getId() );
    // 获取一个对象列表
//    List<Customer> userList = session.selectList(statement2);
    List<Customer> userList = testMapper.getUserList(customer);
    for(int i = 0; i < userList.size(); i++) {
      System.out.println(userList.get(i).getName());
    }
  }

}
