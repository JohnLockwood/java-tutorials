package com.codesolid.tutorials.model.dto;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;

public class TimeSlip {
    private long id;
    private long taskId;
    private DateTime startTime;
    private int secondsElapsed;

    private String timeZone;
    private int timeOffset;
    private long userId;
    private Boolean isTimerRunning = true;
    private String userName;

    public TimeSlip() {

    }

    /*public TimeSlip(TimeSlipJSON tsJSON) {
        setTaskId(tsJSON.getTaskId());
        setStartTime(new DateTime(tsJSON.getStartTime()));
        setSecondsElapsedFromJSON(tsJSON.getStartTime(), tsJSON.getEndTime());
        setTimeOffset(tsJSON.getTimeZoneOffset());
        setTimerRunning(tsJSON.isTimerRunning());
    }
    */

    private void setSecondsElapsedFromJSON(long start, long end) {
        int MILLIS_PER_SECOND = 1000;
        long startSeconds = start / MILLIS_PER_SECOND;
        long endSeconds = end / MILLIS_PER_SECOND;
        setSecondsElapsed((int) (endSeconds - startSeconds));
    }

    public void setTimerRunning(Boolean running) {
        isTimerRunning = running;
    }

    public Boolean isTimerRunning() {
        return isTimerRunning;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime time) {
        this.startTime = time;

    }

    public DateTime calculateOriginalLocalTime(String timeAsUnixTime) {
        Long originalUnixTime = Long.parseLong(timeAsUnixTime);
        return new DateTime(originalUnixTime - getTimeOffset());
    }

    public DateTime calculateOriginalTime(String timeAsUnixTime) {
        Long originalUnixTime = Long.parseLong(timeAsUnixTime);
        return new DateTime(originalUnixTime);
    }

    public int getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    public int getSecondsElapsed() {
        return secondsElapsed;
    }

    public void setSecondsElapsed(int secondsElapsed) {
        this.secondsElapsed = secondsElapsed;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Period getPeriod() {
        Duration elapsed = new Duration(secondsElapsed * 1000);
        return new Period(elapsed);
    }

    public void setPeriod(Period period) {
        setSecondsElapsed(period.toStandardSeconds().getSeconds());
    }
}
