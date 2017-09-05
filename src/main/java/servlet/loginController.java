package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.mysql.cj.api.jdbc.JdbcConnection;
import net.sf.json.JSONObject;

import java.sql.*;

@WebServlet(
        name = "login",
        urlPatterns = {"/Login.webTest"},
        loadOnStartup = 1
)
public class loginController extends HttpServlet {

  private Connection con = null; //定义一个MYSQL链接对象

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    System.out.println("username=" + username + "; password=" + password);

    JSONObject result = new JSONObject();

    try {
      System.out.println("===============================打开数据库连接===================================");
      Class.forName("com.mysql.jdbc.Driver").newInstance(); //MYSQL注册驱动程序
      con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest?characterEncoding=utf-8&serverTimezone=UTC", "root", "123"); //链接本地MYSQL

      Statement stmt; //创建声明
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
      String selectSql = "SELECT sno FROM students where sname='刘华'";
      ResultSet selectRes = stmt.executeQuery(selectSql);
//      while (selectRes.next()) { //循环输出结果集
//        String sno = selectRes.getString("sno");
////        String sname = selectRes.getString("sname");
//        System.out.print("\r\n\r\n");
////        System.out.print("username:" + sno + "password:" + sname);
//        System.out.println("sno=" + sno);
//      }
      selectRes.last(); //结果集指针知道最后一行数据
      int n = selectRes.getRow();
      System.out.println(n);
      if(n > 0) {
        result.put("sno", selectRes.getString("sno"));
        result.put("message", "成功");
        result.put("success", true);
      }

    } catch (Exception e) {
      System.out.print("MYSQL ERROR:" + e.getMessage());
    } finally {
      try {
        con.close();
        System.out.println("================数据库连接已关闭=========================");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    res.setContentType("application/json;charset=UTF-8");
    res.setCharacterEncoding("UTF-8");

    PrintWriter pw = res.getWriter();
    pw.println(result);

  }
}
