package service;

import bean.Customer;
import dao.CustomerDao;
import mapper.CustomerMapper;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerService {

  CustomerDao customerDao = new CustomerDao();
  CustomerMapper customerMapper = new CustomerMapper();

  public JSONObject query(Customer customer) {
//    if(customer.getName().equals(""))
//      System.out.println("空字符串");
    System.out.println("查询页数：" + customer.getPageNumber());
    System.out.println("每页条数：" + customer.getPageSize());
    JSONObject result = new JSONObject();
    String limit = " limit " + (customer.getPageNumber()-1)*customer.getPageSize() + "," + customer.getPageSize();
    if(customer.getName() != null) { // 姓名模糊查询
      String sql = " from user where name like '%" + customer.getName() + "%'";
      ArrayList list = customerDao.query("select *" + sql + limit);
      int total = customerDao.queryTotal("select count(*) as count" + sql);
      result.put("pages", Math.ceil((double) total/customer.getPageSize()));
      result.put("rows", list);
      return result;
    }else if(new Integer(customer.getId()) != null) { // 通过id查找单条数据
      String sql = " from user where id=" + customer.getId();
      result.put("rows", customerDao.query("select *" + sql));
      return result;
    }else { // 查询所有数据
      String sql = " from user" + limit;
      int total = customerDao.queryTotal("select count(*) as count from user");
      result.put("pages", Math.ceil((double) total/customer.getPageSize()));
      result.put("rows", customerDao.query("select *" + sql));
      return result;
    }
  }

  public int insert(Customer customer) {
    String insertSql = "insert into user (name,sex,age) values('" + customer.getName() + "'," + customer.getSex() + "," + customer.getAge() + ")";
    return customerDao.executeUpdate(insertSql);
  }

  public int delete(int id) {
    String deleteSql = "delete from user where id=" + id;
    return customerDao.executeUpdate(deleteSql);
  }

  public int update(Customer customer) {

    return customerMapper.update("mapper.CustomerMapper.updateCustomer", customer);

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
