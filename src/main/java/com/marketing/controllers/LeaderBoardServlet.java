package com.marketing.controllers;

import com.marketing.bean.LeaderboardBean;
import com.marketing.entity.Answer;
import com.marketing.entity.Leaderboard;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LeaderBoardServlet", value = "/LeaderboardServlet")
public class LeaderBoardServlet extends RendererServlet{

    @EJB
    private LeaderboardBean leaderboardBean;

    public LeaderBoardServlet() {
        super("/WEB-INF/leaderboard.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> vars = new HashMap<>();
        List<Leaderboard> leaderboard = leaderboardBean.getLeaderboard();
        vars.put("leaderboard",leaderboard);
        renderAndServeWithVariables(request, response, vars);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
