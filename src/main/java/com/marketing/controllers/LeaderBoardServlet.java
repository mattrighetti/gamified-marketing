package com.marketing.controllers;

import com.marketing.bean.UserBean;
import com.marketing.entity.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "LeaderBoardServlet", value = "/LeaderboardServlet")
public class LeaderBoardServlet extends RendererServlet {

    @EJB
    private UserBean userBean;

    public LeaderBoardServlet() {
        super("/WEB-INF/leaderboard.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> vars = new HashMap<>();
        List<User> leaderboard = userBean.getUsersByScore();
        vars.put("leaderboard", leaderboard);
        renderAndServeWithVariables(request, response, vars);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
