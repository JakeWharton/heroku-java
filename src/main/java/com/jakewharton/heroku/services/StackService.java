package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.Stack;

public class StackService extends HerokuApiService {
    /**
     * List available stacks for an app.
     *
     * @param app The app name.
     */
    public ListBuilder list(String app) {
        return new ListBuilder(this).app(app);
    }

    /**
     * Migrate an app to a new stack.
     *
     * @param app The app name.
     * @param body The target stack.
     */
    public MigrateBuilder migrate(String app, String body) {
        return new MigrateBuilder(this).app(app).body(body);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<Stack>> {
        private static final String URI = "/apps/:app/stack";

        private ListBuilder(StackService service) {
            super(service, TypeHolder.stackList(), URI);
        }

        /** The app name. */
        public ListBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
    public static final class MigrateBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/stack";

        private MigrateBuilder(StackService service) {
            super(service, TypeHolder.string(), URI);
        }

        /** The app name. */
        public MigrateBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The target stack. */
        public MigrateBuilder body(String body) {
            super.body("body", body);
            return this;
        }
    }
}
