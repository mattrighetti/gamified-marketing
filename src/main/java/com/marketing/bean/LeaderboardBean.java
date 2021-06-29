package com.marketing.bean;

import com.marketing.entity.Leaderboard;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class LeaderboardBean extends AbstractFacade<Leaderboard> {

    public LeaderboardBean() {
        super(Leaderboard.class);
    }

    public List<Leaderboard> getLeaderboard() {
        List<Leaderboard> leaderboard = getEntityManager()
                .createNamedQuery("Leaderboard.getOrderedLeaderboard", Leaderboard.class)
                .getResultList();
        if (leaderboard != null)
            return leaderboard;
        else
            return new LinkedList<>();
    }

}
