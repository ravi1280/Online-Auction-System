package lk.jiat.ee.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.jiat.ee.session.AuctionBean;
import lk.jiat.ee.entity.AuctionItem;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @EJB
    private AuctionBean auctionBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AuctionItem> items = auctionBean.getAllItems();
        request.setAttribute("items", items);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
