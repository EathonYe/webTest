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
        name = "CustomerController",
        urlPatterns = {"/operate.user.webTest"}
)
public class CustomerController extends HttpServlet {

  private CustomerService customerService = new CustomerService();

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    String flag = request.getParameter("flag");

    // 将请求数据封装成实体类
    Customer customer = new Customer();

    Integer pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
    if(pageNumber != null) {
      customer.setPageNumber(pageNumber);
    }
    Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
    if(pageSize != null) {
      customer.setPageSize(pageSize);
    }


    String name = request.getParameter("name");
    if(name != null) {
      customer.setName(name);
    }
    if(request.getParameter("age") != null) {
      int age = Integer.parseInt(request.getParameter("age"));
      customer.setAge(age);
    }
    if(request.getParameter("sex") != null) {
      int sex = Integer.parseInt(request.getParameter("sex"));
      customer.setSex(sex);
    }
    if(request.getParameter("id") != null) {
      int id = Integer.parseInt(request.getParameter("id"));
      customer.setId(id);
    }

    JSONObject result = new JSONObject();

    if(flag.equals("query")) { // 查询处理

      JSONObject res = customerService.query(customer);
      res.put("success", true);
      result = res;
    }

    response.setContentType("application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    PrintWriter pw = response.getWriter();
    pw.println(result.toString());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    String flag = request.getParameter("flag");

    // 将请求数据封装成实体类
    Customer customer = new Customer();

    String name = request.getParameter("name");
    customer.setName(name);
    if(request.getParameter("age") != null) {
      int age = Integer.parseInt(request.getParameter("age"));
      customer.setAge(age);
    }
    if(request.getParameter("sex") != null) {
      int sex = Integer.parseInt(request.getParameter("sex"));
      customer.setSex(sex);
    }
    if(request.getParameter("id") != null) {
      int id = Integer.parseInt(request.getParameter("id"));
      customer.setId(id);
    }

    JSONObject result = new JSONObject();

    if(flag.equals("delete")) { // 删除处理
      int res = customerService.delete(customer.getId());
      if(res == 0) {
        result.put("success", false);
      }else if(res > 0) {
        result.put("success", true);
      }
    } else if(flag.equals("insert")) { // 插入处理

      int res = customerService.insert(customer);
      if(res == 0) {
        result.put("success", false);
      }else if(res > 0) {
        result.put("success", true);
      }

    } else if(flag.equals("update")) { // 更新处理
      int res = customerService.update(customer);
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

