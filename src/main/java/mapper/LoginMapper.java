package mapper;

import bean.Admin;
import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginMapper {

  public LoginMapper() throws IOException {
  }

  public List<Admin> selectByName(Admin admin) {
    SqlSession session = MybatisUtils.getSqlSession();

    List<Admin> adminList = new ArrayList<Admin>();

    try {

      String statement = "mapper.LoginMapper.getAdminList";//映射sql的标识字符串

      adminList = session.selectList(statement, admin); // 获取一个对象列表

    } catch (Exception e) {

      e.printStackTrace();

    } finally {

      session.close();

    }

    for(int i=0; i<adminList.size(); i++) {
      Admin admin1 = adminList.get(i);
      System.out.println(admin1.getUsername() + ' ' + admin1.getPassword() + ' ' + admin1.getLoginCount() + ' ' + admin1.getId());
    }

    return adminList;
  }

  public int update(Admin admin) {

    SqlSession session = MybatisUtils.getSqlSession();

    int result = 0;

    try {

      String statement = "mapper.LoginMapper.updateAdmin";

      result = session.update(statement, admin);

      session.commit();

    } catch (Exception e) {

      e.printStackTrace();

    } finally {

      session.close();

    }

    return result;
  }
}
