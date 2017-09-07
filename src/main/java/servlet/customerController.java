package servlet;

import bean.Customer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.CustomerService;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(
        name = "customerController",
        urlPatterns = {"/operate.user.webTest"}
)
public class customerController extends HttpServlet {

  private CustomerService customerService = new CustomerService();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    String flag = request.getParameter("flag");
    JSONObject result = new JSONObject();
    if(flag.equals("query")) {

      ArrayList res = customerService.query();
      result.put("rows", res);
      result.put("success", true);

    } else if(flag.equals("insert")) {

      String name = request.getParameter("name");
      int age = Integer.parseInt(request.getParameter("age"));
      int sex = Integer.parseInt(request.getParameter("sex"));
      Customer customer = new Customer();
      customer.setName(name);
      customer.setSex(sex);
      customer.setAge(age);

      int res = customerService.insert(customer);
      if(res == 0) {
        result.put("success", false);
      }else if(res > 0) {
        result.put("success", true);
      }

    }

    response.setContentType("application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter pw = response.getWriter();
    pw.println(result.toString());
  }

}

//class QueryUser extends HttpServlet {
//  private CustomerService customerService = new CustomerService();
//
//  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    request.setCharacterEncoding("utf-8");
//    ArrayList result = customerService.query();
//
//    response.setContentType("application/json;charset=UTF-8");
//    response.setCharacterEncoding("UTF-8");
//    PrintWriter pw = response.getWriter();
//    pw.println(JSONArray.fromObject(result).toString());
//  }
//}
