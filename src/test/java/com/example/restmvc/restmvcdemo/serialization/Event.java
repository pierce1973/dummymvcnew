package com.example.restmvc.restmvcdemo.serialization;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Event {


    public Event() {
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    String event;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss.sss a z")
    Date date;

    public Event(String event, Date date) {
        this.event = event;
        this.date = date;
    }

}
