package com.app.nursecalling.model;

import com.orm.SugarRecord;

import java.io.Serializable;

public class EventsModel extends SugarRecord implements Serializable {
    public long id;
    public String eventName;
    public String systemName;

    public String username;
    public String time;

    public EventsModel(){}

    public EventsModel(long id, String eventName, String systemName, String username, String time) {
        this.id = id;
        this.eventName = eventName;
        this.systemName = systemName;
        this.username = username;
        this.time = time;
    }
}
