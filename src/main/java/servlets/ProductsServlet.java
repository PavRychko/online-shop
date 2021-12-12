package servlets;

import generators.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> parameters = new HashMap<>();
        PageGenerator pageGenerator = PageGenerator.instance();

        try {
            String page = pageGenerator.getPage("productsTable.html", parameters);
            resp.getWriter().write(page);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
