package com.shop.controller;

public class HomeController {
    import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu GET từ người dùng
        // Điều hướng đến trang chủ và hiển thị thông tin tương ứng

        // Lấy thông tin cần hiển thị từ cơ sở dữ liệu hoặc bất kỳ nguồn dữ liệu nào khác
        String message = "Chào mừng bạn đến với trang chủ";

        // Đặt thông tin vào request attribute để truyền cho view
        request.setAttribute("message", message);

        // Forward đến trang chủ (view) để hiển thị
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lý yêu cầu POST từ người dùng
        // Điều hướng và xử lý dữ liệu được gửi từ form

        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Thực hiện xử lý dữ liệu, ví dụ: đăng nhập, đăng ký, vv.

        // Điều hướng đến trang kết quả (thành công, thất bại, vv.)
        response.sendRedirect("result.jsp");
    }
}



