package com.jakewharton.heroku;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.type.TypeReference;

import com.jakewharton.apibuilder.ApiService;
import com.jakewharton.heroku.util.Base64;

/**
 * Heroku-specific API service extension which facilitates provides helper
 * methods for performing remote method calls as well as deserializing the
 * corresponding JSON responses.
 *
 * @author Jake Wharton <jakewharton@gmail.com>
 */
public abstract class HerokuApiService extends ApiService {
    /** HTTP header name for authorization. */
    private static final String HEADER_AUTHORIZATION = "Authorization";
    /** HTTP header name for accept. */
    private static final String HEADER_ACCEPT = "Accept";
    /** HTTP authorization type. */
    private static final String HEADER_AUTHORIZATION_TYPE = "Basic";
    /** JSON type for HTTP accept header. */
    private static final String TYPE_JSON = "application/json";
    /** ObjectMapper singleton. */
    private static ObjectMapper MAPPER = null;


    public HerokuApiService() {
        addRequestHeader(HEADER_ACCEPT, TYPE_JSON);
    }


    /**
     * Execute an HTTP request.
     *
     * @param url URL endpoint.
     * @param body POST body.
     * @param method HTTP method verb.
     * @return Response stream.
     */
    public InputStream execute(String url, String body, String method, int expected) {
        return executeMethod(url, body, null, method, expected);
    }

    /**
     * Set API key to use for client authentication by Heroku.
     *
     * @param value Value.
     */
    public void setApiKey(String value) {
        final String source = ":" + value;
        final String authentication = HEADER_AUTHORIZATION_TYPE + " " + Base64.encodeBytes(source.getBytes());

        addRequestHeader(HEADER_AUTHORIZATION, authentication);
    }

    /**
     * Use Jackson to deserialize a JSON object to a native class representation.
     *
     * @param <T> Native class type.
     * @param typeToken Native class type wrapper.
     * @param response Serialized JSON object.
     * @return Deserialized native instance.
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    protected <T> T unmarshall(InputStream response, TypeReference<T> typeToken) throws JsonParseException, JsonMappingException, IOException {
        return (T)HerokuApiService.getObjectMapper().readValue(response, typeToken);
    }

    /**
     * Use Jackson to deserialize a JSON string to a native class representation.
     *
     * @param <T> Native class type.
     * @param typeToken Native class type wrapper.
     * @param reponse Serialized JSON string.
     * @return Deserialized native instance.
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    protected <T> T unmarshall(String response, Class<?> type) throws JsonParseException, JsonMappingException, IOException {
        return (T)HerokuApiService.getObjectMapper().readValue(response, type);
    }

    /**
     * Create am {@link ObjectMapper} and register all of the custom types needed
     * in order to properly deserialize complex Heroku-specific types.
     *
     * @return Assembled Jackson mapper instance.
     */
    public static ObjectMapper getObjectMapper() {
        if (MAPPER == null) {
            MAPPER = createObjectMapper();
        }
        return MAPPER;
    }

    private static ObjectMapper createObjectMapper() {
        /*GsonBuilder builder = new GsonBuilder();*/
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule herokuModule = new SimpleModule("heroku", new Version(1, 0, 0, "SNAPSHOT"));

        final DateFormat dateFormat = new SimpleDateFormat("y/M/d H:m:s Z");
        herokuModule.addDeserializer(Calendar.class, new JsonDeserializer<Calendar>() {
            @Override
            public Calendar deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
                try {
                    Calendar c = Calendar.getInstance();
                    c.setTime(dateFormat.parse(arg0.getText()));
                    return c;
                } catch (ParseException e) {
                    return null;
                }
            }
        });

        mapper.registerModule(herokuModule);
        return mapper;
    }
}
