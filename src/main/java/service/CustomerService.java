package service;

import api.entity.Customer;
import api.mapper.CustomerMapper;
import api.mapper.LoginMapper;
import dao.CustomerDao;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSession;
import utils.MybatisUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

  public JSONObject query(Customer customer) {

    System.out.println("查询页数：" + customer.getPageNumber());
    System.out.println("每页条数：" + customer.getPageSize());

    JSONObject result = new JSONObject();

    SqlSession session = MybatisUtils.getSqlSession();

    try {

      CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);

      result.put("rows", customerMapper.selectCustomer(customer, (customer.getPageNumber()-1)*customer.getPageSize()));
      int total = customerMapper.getTotal(customer);
      result.put("pages", Math.ceil((double) total/customer.getPageSize()));

    } catch (Exception e) {

      e.printStackTrace();

    } finally {

      session.close();

    }

    return result;

  }

// 原生实现分页查询
//  public JSONObject query(Customer customer) {
//    CustomerDao customerDao = new CustomerDao();
//
//    System.out.println("查询页数：" + customer.getPageNumber());
//    System.out.println("每页条数：" + customer.getPageSize());
//
//    JSONObject result = new JSONObject();
//
//    SqlSession session = MybatisUtils.getSqlSession();
//
//    String limit = " limit " + (customer.getPageNumber()-1)*customer.getPageSize() + "," + customer.getPageSize();
//    if(customer.getName() != null) { // 姓名模糊查询
//      String sql = " from user where name like '%" + customer.getName() + "%'";
//      ArrayList list = customerDao.query("select *" + sql + limit);
//      int total = customerDao.queryTotal("select count(*) as count" + sql);
//      result.put("pages", Math.ceil((double) total/customer.getPageSize()));
//      result.put("rows", list);
//      return result;
//    }else if(new Integer(customer.getId()) != null) { // 通过id查找单条数据
//      // 原生实现单条数据的查询
//      String sql = " from user where id=" + customer.getId();
//      result.put("rows", customerDao.query("select *" + sql));
//      return result;
//
//    }else { // 查询所有数据
//      // 原生实现分页查询
//      String sql = " from user" + limit;
//      int total = customerDao.queryTotal("select count(*) as count from user");
//      result.put("pages", Math.ceil((double) total/customer.getPageSize()));
//      result.put("rows", customerDao.query("select *" + sql));
//      return result;
//    }
//  }

  public int insert(Customer customer) {

    SqlSession session = MybatisUtils.getSqlSession();

    int result = 0;

    try {

      CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);

      result = customerMapper.insertCustomer(customer);

    } catch (Exception e) {

      e.printStackTrace();

    } finally {

      session.close();

    }

    return result;

    // 原生实现插入
//    String insertSql = "insert into user (name,sex,age) values('" + customer.getName() + "'," + customer.getSex() + "," + customer.getAge() + ")";
//    return customerDao.executeUpdate(insertSql);
  }

  public int delete(int id) {

    SqlSession session = MybatisUtils.getSqlSession();

    int result = 0;

    try {

      CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);

      result = customerMapper.deleteCustomer(id);

    } catch (Exception e) {

      e.printStackTrace();

    } finally {

      session.close();

    }

    return result;

    // 原生实现删除
//    String deleteSql = "delete from user where id=" + id;
//    return customerDao.executeUpdate(deleteSql);
  }

  public int update(Customer customer) {

    SqlSession session = MybatisUtils.getSqlSession();

    int result = 0;

    try {

      CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);

      result = customerMapper.updateCustomer(customer);

    } catch (Exception e) {

      e.printStackTrace();

    } finally {

      session.close();

    }

    return result;

    // 原生实现更新
//    String updateSql = "update user set ";
//    if(customer.getName() != null) {
//      updateSql += "name='" + customer.getName() + "'";
//    }
//    if(new Integer(customer.getSex()) != null) {
//      updateSql += ",sex=" + customer.getSex();
//    }if(new Integer(customer.getAge()) != null) {
//      updateSql += ",age=" + customer.getAge();
//    }
//    updateSql += " where id = " + customer.getId();
//    return customerDao.executeUpdate(updateSql);

  }

}
