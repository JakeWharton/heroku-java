package com.jakewharton.heroku.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class Addon {
    public String name;

    public String url;

    public String id;

    public boolean beta;

    @JsonProperty("price_unit")
    public String priceUnit;

    public String description;

    public String state;

    @JsonProperty("price_cents")
    public int priceCents;

    /** Only included when in the context of an app. */
    public Boolean configured;
}
