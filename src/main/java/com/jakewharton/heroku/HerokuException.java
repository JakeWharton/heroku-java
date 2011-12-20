package com.jakewharton.heroku;

import java.util.Map;

import com.jakewharton.apibuilder.ApiException;
import com.jakewharton.heroku.entities.Response;

public final class HerokuException extends RuntimeException {
    private static final long serialVersionUID = 6158978902757706299L;

    public final String url;
    public final Map<String, String> body;
    public final Response response;

    public HerokuException(String url, ApiException cause) {
        this(url, null, cause);
    }
    public HerokuException(String url, Map<String, String> body, ApiException cause) {
        super(cause);
        this.url = url;
        this.body = body;
        this.response = null;
    }
    public HerokuException(String url, Map<String, String> body, ApiException cause, Response response) {
        super(response.error, cause);
        this.url = url;
        this.body = body;
        this.response = response;
    }
}
