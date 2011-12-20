package com.jakewharton.heroku.services;

import java.util.Map;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;

public class ConfigService extends HerokuApiService {
    /**
     * List config vars for an app.
     *
     * @param app The app name.
     */
    public ListBuilder list(String app) {
        return new ListBuilder(this).app(app);
    }

    /**
     * Add config vars to an app.
     *
     * @param app The app name.
     * @param config The new config vars.
     */
    public AddBuilder add(String app, Map<String, String> config) {
        return new AddBuilder(this).app(app).config(config);
    }

    /**
     * Remove a config var from an app.
     *
     * @param app The app name.
     * @param key The config var to remove.
     */
    public DeleteBuilder delete(String app, String key) {
        return new DeleteBuilder(this).app(app);
    }


    public static final class ListBuilder extends HerokuApiBuilder<Map<String, String>> {
        private static final String URI = "/apps/:app/config_vars";

        private ListBuilder(ConfigService service) {
            super(service, TypeHolder.map(), URI);
        }

        /** The app name. */
        public ListBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
    public static final class AddBuilder extends HerokuApiBuilder<Map<String, String>> {
        private static final String URI = "/apps/:app/config_vars";

        private AddBuilder(ConfigService service) {
            super(service, TypeHolder.map(), URI);
        }

        /** The app name. */
        public AddBuilder app(String app) {
            field(":app", app);
            return this;
        }
        public AddBuilder config(Map<String, String> config) {
            body("body", config);
            return this;
        }
    }
    public static final class DeleteBuilder extends HerokuApiBuilder<Map<String, String>> {
        private static final String URI = "/apps/:app/config_vars";

        private DeleteBuilder(ConfigService service) {
            super(service, TypeHolder.map(), URI);
        }

        /** The app name. */
        public DeleteBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
}
