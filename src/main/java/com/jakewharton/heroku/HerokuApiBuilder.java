package com.jakewharton.heroku;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;
import com.jakewharton.apibuilder.ApiException;
import com.jakewharton.heroku.entities.Response;

/**
 * Heroku-specific API builder extension which provides helper methods for
 * adding fields, parameters, and post-parameters commonly used in the API.
 *
 * @param <T> Native class type of the HTTP method call result.
 * @author Jake Wharton <jakewharton@gmail.com>
 */
public abstract class HerokuApiBuilder<T> {
    /** Heroku API URL base. */
    private static final String BASE_URL = "https://api.heroku.com";


    /** Valid HTTP request methods. */
    protected static enum HttpMethod {
        GET, POST, PUT, DELETE
    }


    /** Method absolute URI. */
    private final String mUri;
    /** Method parameters. */
    private final Map<String, String> mUriParams;
    /** Expected HTTP response status code. */
    private final int mExpected;
    /** Service instance. */
    private final HerokuApiService mService;
    /** Type token of return type. */
    private final TypeReference<T> mType;
    /** HTTP request method to use. */
    private final HttpMethod mMethod;
    /** Map representation of body. */
    private final Map<String, String> mBody;


    /**
     * Initialize a new builder for an HTTP GET call.
     *
     * @param service Service to bind to.
     * @param token Return type token.
     * @param methodUri URI method format string.
     */
    public HerokuApiBuilder(HerokuApiService service, TypeReference<T> token, String methodUri) {
        this(service, token, methodUri, HttpMethod.GET);
    }

    /**
     * Initialize a new builder for the specified HTTP method call.
     *
     * @param service Service to bind to.
     * @param token Return type token.
     * @param methodUri URL format string.
     * @param method HTTP method.
     */
    public HerokuApiBuilder(HerokuApiService service, TypeReference<T> token, String methodUri, HttpMethod method) {
        this(service, token, methodUri, method, HttpURLConnection.HTTP_OK);
    }

    public HerokuApiBuilder(HerokuApiService service, TypeReference<T> token, String methodUri, HttpMethod method, int expected) {
        mUri = BASE_URL + methodUri;
        mUriParams = new HashMap<String, String>();
        mExpected = expected;

        mService = service;

        mType = token;
        mMethod = method;
        mBody = new HashMap<String, String>();
    }


    /**
     * Execute remote API method and unmarshall the result to its native type.
     *
     * @return Instance of result type.
     * @throws ApiException if validation fails.
     */
    @SuppressWarnings("unchecked")
    public final T fire() {
        preFireCallback();

        try {
            performValidation();
        } catch (Exception e) {
            throw new ApiException(e);
        }

        T result = null;
        if (mType.getClass().equals(String.class)) {
            result = (T)executeString();
        } else if (mType != null) {
             try {
                result = mService.unmarshall(execute(), mType);
            } catch (JsonParseException e) {
                throw new ApiException(e);
            } catch (JsonMappingException e) {
                throw new ApiException(e);
            } catch (IOException e) {
                throw new ApiException(e);
            }
        }

        postFireCallback(result);
        return result;
    }

    /**
     * Perform any required actions before validating the request.
     */
    protected void preFireCallback() {
        //Override me!
    }

    /**
     * Perform any required validation before firing off the request.
     */
    protected void performValidation() {
        //Override me!
    }

    /**
     * Perform any required actions before returning the request result.
     *
     * @param result Request result.
     */
    protected void postFireCallback(T result) {
        //Override me!
    }

    /**
     * <p>Execute the remote API method and return the JSON object result.<p>
     *
     * <p>This method can be overridden to select a specific subset of the JSON
     * object. The overriding implementation should still call 'super.execute()'
     * and then perform the filtering from there.</p>
     *
     * @return JSON object instance.
     */
    private final InputStream execute() {
        String url = buildUrl();
        while (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        try {
            final String body = (mBody.size() > 0) ? buildBody() : null;
            return mService.execute(url, body, mMethod.toString(), mExpected);
        } catch (ApiException ae) {
            try {
                Response response = mService.unmarshall(ae.getMessage(), Response.class);
                if (response != null) {
                    throw new HerokuException(url, mBody, ae, response);
                }
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            throw new HerokuException(url, mBody, ae);
        }
    }

    private final String executeString() {
        try {
            final char[] buffer = new char[0x10000];
            StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(execute(), "UTF-8");
            int read;
            do {
                read = in.read(buffer, 0, buffer.length);
                if (read > 0) {
                    out.append(buffer, 0, read);
                }
            } while (read >= 0);
            return out.toString();
        } catch (UnsupportedEncodingException e) {
            throw new ApiException(e);
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }

    /**
     * Print the HTTP request that would be made.
     */
    public final void printRequest() {
        preFireCallback();

        try {
            performValidation();
        } catch (Exception e) {
            throw new ApiException(e);
        }

        String url = buildUrl();
        while (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        System.out.println(mMethod.toString().toUpperCase() + " " + url);
        for (String name : mService.getRequestHeaderNames()) {
            System.out.println(name + ": " + mService.getRequestHeader(name));
        }

        switch (mMethod) {
            case PUT:
            case POST:
                System.out.println();
                try {
                    System.out.println(HerokuApiService.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(mBody));
                } catch (Exception e) {
                    System.out.println("ERROR: Could not print post body.");
                    e.printStackTrace();
                }
                break;
        }
    }

    public final void printResponse() {
        final String out = executeString();
        try {
            System.out.println(HerokuApiService.getObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(out));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String buildUrl() {
        final StringBuilder builder = new StringBuilder(mUri);
        for (Entry<String, String> entry : mUriParams.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();
            final int keyLength = key.length();
            final int valueLength = value.length();
            int indexFrom = 0;
            while ((indexFrom = builder.indexOf(key, indexFrom)) >= 0) {
                builder.replace(indexFrom, indexFrom + keyLength, value);
                indexFrom += valueLength;
            }
        }
        return builder.toString();
    }

    private final String buildBody() {
        final StringBuilder builder = new StringBuilder();
        for (Entry<String, String> entry : mBody.entrySet()) {
            builder.append(entry.getKey())
                   .append('=')
                   .append(entry.getValue())
                   .append('&');
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public final void field(String key, String value) {
        try {
            mUriParams.put(key, URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new ApiException(e);
        }
    }

    public final void body(String key, String value) {
        try {
            mBody.put(key, URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new ApiException(e);
        }
    }
    public final void body(String key, Map<String, String> value) {
        try {
            body(key, HerokuApiService.getObjectMapper().writeValueAsString(value));
        } catch (JsonGenerationException e) {
            throw new ApiException(e);
        } catch (JsonMappingException e) {
            throw new ApiException(e);
        } catch (IOException e) {
            throw new ApiException(e);
        }
    }
}
