package com.marketing.bean;

import com.marketing.entity.Leaderboard;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Stateless
public class LeaderboardBean extends AbstractFacade{

    public LeaderboardBean() {
        super(Leaderboard.class);
    }

    public List<Leaderboard> getLeaderboard(){
        List<Leaderboard> leaderboard = getEntityManager()
                                .createNamedQuery("Leaderboard.getOrderedLeaderboard")
                                .getResultList();
        if(leaderboard != null) return leaderboard;
        else return new LinkedList<>();
    }

}
