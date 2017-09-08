package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;

import java.sql.*;

@WebServlet(
        name = "login",
        urlPatterns = {"/Login.webTest"},
        loadOnStartup = 1
)
public class loginController extends HttpServlet {

  private Connection con = null; //定义一个MYSQL链接对象
  private Statement stmt = null; //创建声明
  private ResultSet selectRes = null; // 保存返回结果

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    System.out.println("username=" + username + "; password=" + password);

    res.setContentType("application/json;charset=UTF-8");
    res.setCharacterEncoding("UTF-8");

    JSONObject result = new JSONObject();

    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL注册驱动程序

      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webtest?characterEncoding=utf-8&serverTimezone=UTC", "root", "123456"); //链接本地MYSQL

      stmt = con.createStatement();

      //新增一条数据
//      stmt.executeUpdate("INSERT INTO user (username, password) VALUES ('init', '123456')");
//      ResultSet res = stmt.executeQuery("select LAST_INSERT_ID()");
//      int ret_id;
//      if (res.next()) {
//        ret_id = res.getInt(1);
//        System.out.print(ret_id);
//      }

      //删除一条数据
//      String sql = "DELETE FROM user WHERE id = 1";
//      long deleteRes = stmt.executeUpdate(sql); //如果为0则没有进行删除操作，如果大于0，则记录删除的条数
//      System.out.print("DELETE:" + deleteRes);

      //更新一条数据
//      String updateSql = "UPDATE user SET username = 'xxxx' WHERE id = 2";
//      long updateRes = stmt.executeUpdate(updateSql);
//      System.out.print("UPDATE:" + updateRes);

      //查询数据并输出
      String selectSql = "SELECT password,id,loginCount FROM login where username=" + username;
      selectRes = stmt.executeQuery(selectSql);
//      while (selectRes.next()) { //循环输出结果集
//        String sno = selectRes.getString("sno");
////        String sname = selectRes.getString("sname");
//        System.out.print("\r\n\r\n");
////        System.out.print("username:" + sno + "password:" + sname);
//        System.out.println("sno=" + sno);
//      }
      selectRes.last(); //结果集指针知道最后一行数据
      int n = selectRes.getRow();
//      System.out.println(n);
      System.out.println(selectRes.getString("数据库取得的密码：" + "password"));
      if(n > 0 && selectRes.getString("password").equals(password)) {
        result.put("message", "成功");
        result.put("success", true);

        PrintWriter pw = res.getWriter();
        pw.println(result);

        int loginCount = Integer.parseInt(selectRes.getString("loginCount"));
        int id = Integer.parseInt(selectRes.getString("id"));
        selectSql = "UPDATE login SET loginCount=" + (loginCount+1) + " where id=" + id;
        long updateRes = stmt.executeUpdate(selectSql);
        System.out.print("UPDATE Count:" + updateRes + "\n");
      }

    } catch (Exception e) {
      System.out.print("MYSQL ERROR:" + e.getMessage());
    } finally {
      try {
        stmt.close();
        selectRes.close();
        con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }
}
