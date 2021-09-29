package controller;

import com.alibaba.fastjson.JSONObject;
import service.MasterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 修正 编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8"); // 注意 , 别写错

        //2. 接收 前端过来的 3个参数
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        //3. 登陆的时候,首先要验证 验证码是否正确
        // 3.1 获取 后台的验证码!

        // 就需要 service / dao 层判断. 如果 咱们业务不是特别的多, 那么
        // 可以直接 不用 service 层
        MasterService service = new MasterService();
        Map map = service.login(account,password,req);
        String jsonString = JSONObject.toJSONString(map);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.close();

    }
}
