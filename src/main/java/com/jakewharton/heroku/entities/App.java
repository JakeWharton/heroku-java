package com.jakewharton.heroku.entities;

import java.util.Calendar;

import org.codehaus.jackson.annotate.JsonProperty;

public class App {
    public long id;

    public String name;

    @JsonProperty("create_status")
    public String createStatus;

    @JsonProperty("created_at")
    public Calendar createdAt;

    public String stack;

    @JsonProperty("requested_stack")
    public String requestedStack;

    @JsonProperty("repo_migrate_status")
    public String repoMigrateStatus;

    @JsonProperty("slug_size")
    public long slugSize;

    @JsonProperty("repo_size")
    public long repoSize;

    public long dynos;

    public long workers;

    @JsonProperty("web_url")
    public String webUrl;

    @JsonProperty("owner_email")
    public String ownerEmail;

    @JsonProperty("git_url")
    public String gitUrl;

    @JsonProperty("domain_name")
    public Domain domainName;
}
