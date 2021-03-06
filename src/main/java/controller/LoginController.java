package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import api.entity.Admin;
import net.sf.json.JSONObject;
import service.LoginService;

import java.util.List;

@WebServlet(
        name = "login",
        urlPatterns = {"/Login.webTest"},
        loadOnStartup = 1
)
public class LoginController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    System.out.println("username=" + username + "; password=" + password);
    Admin admin = new Admin();
    admin.setUsername(username);
    admin.setPassword(password);

    res.setContentType("application/json;charset=UTF-8");
    res.setCharacterEncoding("UTF-8");

    JSONObject result = new JSONObject();

    List<Admin> adminList = new LoginService().selectByName(admin);

    if(adminList.size() > 0) {
      for(int i = 0; i < adminList.size(); i++) {
        if(admin.getPassword().equals(adminList.get(i).getPassword())) {
          result.put("message", "验证成功");
          result.put("success", true);
          admin.setLoginCount(adminList.get(i).getLoginCount());
          admin.setId(adminList.get(i).getId());

          break;

        }
      }
    }else {
      result.put("success", false);
      result.put("message", "用户名或密码错误!!!");
    }

    PrintWriter pw = res.getWriter();
    pw.println(result);

    if(result.get("success").equals(true)) {
      // 加入session
      // 登陆次数加1
      admin.setLoginCount(admin.getLoginCount() + 1);
      int count = new LoginService().updateLoginCount(admin);
      System.out.println("loginCount是否更新成功:" + count);
    }

  }
}
