package lk.jiat.ee.web.servlet;

import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lk.jiat.ee.session.AuctionBean;
import lk.jiat.ee.util.BidMessage;
import java.io.IOException;


@WebServlet("/bbid")
public class BidServlet extends HttpServlet {

    @Resource(lookup = "jms/bidQueue")
    private Queue bidQueue;

    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Inject
    private AuctionBean session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long itemId = Long.parseLong(request.getParameter("id"));
        double bid = Double.parseDouble(request.getParameter("amount"));

        try (JMSContext context = connectionFactory.createContext()) {
            ObjectMessage message = context.createObjectMessage(new BidMessage(itemId, bid));
            context.createProducer().send(bidQueue, message);
            session.updateBid(itemId, bid);
            System.out.println("bid updated"+itemId+" "+bid);
        }

        response.sendRedirect("home");

    }
}