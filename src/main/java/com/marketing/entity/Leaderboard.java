package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "leaderboard", schema = "gamified_marketing")
@NamedQuery(name = "Leaderboard.getOrderedLeaderboard", query = "select l from Leaderboard l order by l.points desc ")
public class Leaderboard {
    private User userId;
    private long points;

    @Id
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "points")
    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }
}
