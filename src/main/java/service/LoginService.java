package service;

import api.entity.Admin;
import api.mapper.LoginMapper;
import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginService {
  /**
   * 通过姓名搜索管理员
   * @param admin
   * @return
   */
  public List<Admin> selectByName(Admin admin) {
    SqlSession session = MybatisUtils.getSqlSession();

    List<Admin> adminList = new ArrayList<Admin>();

    try {

      LoginMapper loginMapper = session.getMapper(LoginMapper.class);

      adminList = loginMapper.selectByName(admin);

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

  /**
   * 更新登陆次数
   * @param admin
   * @return
   */
  public int updateLoginCount(Admin admin) {
    SqlSession session = MybatisUtils.getSqlSession();

    int result = 0;

    try {

      LoginMapper loginMapper = session.getMapper(LoginMapper.class);

      result = loginMapper.updateAdmin(admin);

    } catch (Exception e) {

      e.printStackTrace();

    } finally {

      session.close();

    }

    return result;
  }
}
