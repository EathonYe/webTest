package service;

import bean.Customer;
import dao.CustomerDao;

import java.util.ArrayList;

public class CustomerService {

  CustomerDao customerDao = new CustomerDao();

  public ArrayList query(Customer customer) {
    if(customer.getName() != null) {
      return customerDao.query("select * from user where name like '%" + customer.getName() + "%'");
    }else if(String.valueOf(customer.getId()) != null) {
      return customerDao.query("select * from user where id=" + customer.getId());
    }else {
      return customerDao.query("select * from user");
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
    String updateSql = "update user set ";
    if(customer.getName() != null) {
      updateSql += "name='" + customer.getName() + "'";
    }
    if(new Integer(customer.getSex()) != null) {
      updateSql += ",sex=" + customer.getSex();
    }if(new Integer(customer.getAge()) != null) {
      updateSql += ",age=" + customer.getAge();
    }
    updateSql += " where id = " + customer.getId();
    return customerDao.executeUpdate(updateSql);
  }

}
