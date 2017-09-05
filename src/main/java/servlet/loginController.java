package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import net.sf.json.JSONObject;

@WebServlet(
        name = "login",
        urlPatterns = {"/Login.webTest"},
        loadOnStartup = 1
)
public class loginController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    System.out.println("username=" + username + "; password=" + password);

    res.setContentType("application/json;charset=UTF-8");
    res.setCharacterEncoding("UTF-8");

    JSONObject result = new JSONObject();
    result.put("message", "成功");
    result.put("success", true);

    PrintWriter pw = res.getWriter();
    pw.println(result);

  }
}
