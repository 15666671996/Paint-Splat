package com.group3.paint_splat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties({"createTime", "startTime", "roomTimer", "gameTimer"})
public class Game {
    private Date createTime;
    private Date startTime;
    private long timeAfterCreate;
    private long timeAfterStart;
    private double step;
    private ArrayList<String> players;
    private ArrayList<Integer> scores;
    private ArrayList<Paint> paints;
    private ArrayList<double[]> boardPositions;
    private boolean isStart;
    private Timer roomTimer;
    private Timer gameTimer;
    private final static long timeSpan = 60000;

    public Game() {
        createTime = new Date();
        timeAfterCreate = 0;
        step = 0.01;
        players = new ArrayList<>();
        scores = new ArrayList<>();
        paints = new ArrayList<>();
        boardPositions = new ArrayList<>();
        timeAfterStart = 0;
        isStart = false;
        init();
    }

    private void init() {
        boardPositions.add(new double[]{0, 0});
        Random random = new Random();
        long timeUse = 0;
        while (timeUse < timeSpan * 2) {
            boardPositions.add(new double[]{random.nextInt(51), random.nextInt(51)});
            timeUse += calculateDistance(boardPositions.get(boardPositions.size() - 1), boardPositions.get(boardPositions.size() - 2)) / step;
        }
    }

    private double calculateDistance(double[] point1, double[] point2) {
        return Math.sqrt((point1[0] - point2[0]) * (point1[0] - point2[0]) + (point1[1] - point2[1]) * (point1[1] - point2[1]));
    }

    public boolean shoot(String playerId, double[] position) {
        Iterator<Paint> iterator = paints.iterator();
        boolean isOverlapped = false;
        while (iterator.hasNext()) {
            Paint next = iterator.next();
            if (calculateDistance(next.getPosition(), position) < 12) {
                isOverlapped = true;
            }
        }
        if (isOverlapped) {
            return false;
        } else {
            paints.add(new Paint(playerId, position));
            scores.set(players.indexOf(playerId), scores.get(players.indexOf(playerId)) + 1);
            return true;
        }
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setTimeAfterCreate(long timeAfterCreate) {
        this.timeAfterCreate = timeAfterCreate;
    }

    public void setTimeAfterStart(long timeAfterStart) {
        this.timeAfterStart = timeAfterStart;
    }

    public void setStep(double step) {
        this.step = step;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public void setRoomTimer(Timer roomTimer) {
        this.roomTimer = roomTimer;
    }

    public void setGameTimer(Timer gameTimer) {
        this.gameTimer = gameTimer;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public long getTimeAfterCreate() {
        return timeAfterCreate;
    }

    public long getTimeAfterStart() {
        return timeAfterStart;
    }

    public double getStep() {
        return step;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public ArrayList<Integer> getScores() {
        return scores;
    }

    public ArrayList<Paint> getPaints() {
        return paints;
    }

    public ArrayList<double[]> getBoardPositions() {
        return boardPositions;
    }

    public boolean isStart() {
        return isStart;
    }

    public Timer getRoomTimer() {
        return roomTimer;
    }

    public Timer getGameTimer() {
        return gameTimer;
    }

}
