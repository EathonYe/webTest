package dao;

import bean.Customer;
import impl.Mapper;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDao {

  private Connection con = null; //定义一个MYSQL链接对象
  private Statement stmt = null; //创建声明
  private ResultSet res = null; // 保存返回结果
//  private ArrayList<Customer> customer = new ArrayList<Customer>(); // 最后的返回对象

  /**
   * 查询数据库并返回结果
   * @param querySql
   * @param mapper
   * @return
   */
//  private ArrayList<Customer> query(String querySql, Mapper mapper) {
//    try {
//      Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL注册驱动程序
//      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webtest?characterEncoding=utf-8&serverTimezone=UTC", "root", "123456"); //链接本地MYSQL
//      stmt = con.createStatement();
//
//      res = stmt.executeQuery(querySql);
//
//      if(res.next()) {
//        customer = mapper.mapRow(res);
//      }
//
//    } catch (Exception e) {
//      System.out.print("MYSQL ERROR:" + e.getMessage());
//    } finally {
//      try {
//        con.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      try {
//        stmt.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//      try {
//        res.close();
//      } catch (SQLException e) {
//        e.printStackTrace();
//      }
//    }
//
//    return customer;
//
//  }

  public ArrayList query(String querySql) {
    ArrayList<Customer> list = new ArrayList<Customer>();
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL注册驱动程序
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webtest?characterEncoding=utf-8&serverTimezone=UTC", "root", "123456"); //链接本地MYSQL
      stmt = con.createStatement();

      res = stmt.executeQuery(querySql);

      while(res.next()) {
        Customer customer = new Customer();
        customer.setId(res.getInt("id"));
        customer.setName(res.getString("name"));
        customer.setAge(res.getInt("age"));
        customer.setSex(res.getInt("sex"));

        list.add(customer);
      }

    } catch (Exception e) {
      System.out.print("MYSQL ERROR:" + e.getMessage());
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        res.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return list;
  }

  public int insert(String insertSql) {
    int result = 0;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL注册驱动程序
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webtest?characterEncoding=utf-8&serverTimezone=UTC", "root", "123456"); //链接本地MYSQL
      stmt = con.createStatement();

      result = stmt.executeUpdate(insertSql);

    } catch (Exception e) {
      System.out.print("MYSQL ERROR:" + e.getMessage());
    } finally {
      try {
        con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        stmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }


}