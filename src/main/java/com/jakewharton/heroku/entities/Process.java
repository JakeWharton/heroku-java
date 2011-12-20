package com.jakewharton.heroku.entities;

import java.util.Calendar;
import org.codehaus.jackson.annotate.JsonProperty;

public class Process {
    public String upid;

    public String process;

    public String type;

    public String command;

    @JsonProperty("app_name")
    public String appName;

    public String slug;

    public String action;

    public String state;

    @JsonProperty("pretty_state")
    public String prettyState;

    public int elapsed;

    @JsonProperty("rendezvous_url")
    public String rendezvousUrl;

    public boolean attached;

    @JsonProperty("transitioned_at")
    public Calendar transitionedAt;
}
