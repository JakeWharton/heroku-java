package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.Addon;

public class LogService extends HerokuApiService {
    /**
     * Get logs for an app.
     *
     * @param app The app name.
     */
    public GetBuilder get(String app) {
        return new GetBuilder(this).app(app);
    }


    public static final class GetBuilder extends HerokuApiBuilder<List<Addon>> {
        private static final String URI = "/apps/:app/logs";

        private GetBuilder(LogService service) {
            super(service, TypeHolder.addonList(), URI);
            body("logplex", "true");
        }

        /** The app name. */
        public GetBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The number of lines to display. */
        public GetBuilder lines(int lines) {
            body("num", Integer.toString(lines));
            return this;
        }
        /** Only display logs from a given process. */
        public GetBuilder process(String process) {
            body("ps", process);
            return this;
        }
        /** Only display logs from a given source. */
        public GetBuilder source(String source) {
            body("sourve", source);
            return this;
        }
        /** Continually stream logs. */
        public GetBuilder tail(boolean tail) {
            if (tail) {
                body("tail", "1");
            }
            return this;
        }
    }
}
