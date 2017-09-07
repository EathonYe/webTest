package service;

import bean.Customer;
import dao.CustomerDao;

import java.util.ArrayList;

public class CustomerService {

  CustomerDao customerDao = new CustomerDao();

  public ArrayList query() {
    return customerDao.query("select * from user");
  }

  public int insert(Customer customer) {
    String insertSql = "insert into user (name,sex,age) values('" + customer.getName() + "'," + customer.getSex() + "," + customer.getAge() + ")";
    return customerDao.insert(insertSql);
  }

}
