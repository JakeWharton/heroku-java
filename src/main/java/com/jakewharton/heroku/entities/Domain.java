package com.jakewharton.heroku.entities;

import java.util.Calendar;

import org.codehaus.jackson.annotate.JsonProperty;

public class Domain {
    public long id;

    @JsonProperty("app_id")
    public long appId;

    public String domain;

    @JsonProperty("base_domain")
    public String baseDomain;

    @JsonProperty("created_at")
    public Calendar createdAt;

    @JsonProperty("updated_at")
    public Calendar updatedAt;

    @JsonProperty("default")
    public String default_;
}