package com.jakewharton.heroku.entities;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class Release {
    public String name;

    @JsonProperty("descr")
    public String description;

    public String user;

    public String commit;

    @JsonProperty("env")
    public Map<String, String> environment;

    public List<String> addons;

    public Map<String, String> pstable;

    @JsonProperty("created_at")
    public Calendar createdAt;
}
