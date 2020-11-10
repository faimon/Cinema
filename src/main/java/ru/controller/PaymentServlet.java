package ru.controller;

import ru.persistance.Account;
import ru.persistance.Place;
import ru.service.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String place = req.getParameter("place");
        int row = Integer.parseInt(place.substring(0, 1));
        int space = Integer.parseInt(place.substring(1, 2));
        String headText = "Вы выбрали ряд " + row + " место " + space + ", Сумма : 500 рублей.";
        req.setAttribute("place", place);
        req.setAttribute("headText", headText);
        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Account account = new Account(0, req.getParameter("username"),
                Integer.parseInt(req.getParameter("phone")), 0);
        PsqlStore.instOf().savePurchasedTicket(account, new Place(0, Integer.parseInt(req.getParameter("place"))));
    }
}
