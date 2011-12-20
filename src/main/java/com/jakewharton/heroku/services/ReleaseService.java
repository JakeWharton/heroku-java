package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.Release;

public class ReleaseService extends HerokuApiService {
    /**
     * List releases for an app.
     *
     * @param app The app name.
     */
    public ListBuilder list(String app) {
        return new ListBuilder(this).app(app);
    }

    /**
     * Get info for a release.
     *
     * @param app The app name.
     * @param release The release name.
     */
    public InfoBuilder info(String app, String release) {
        return new InfoBuilder(this).app(app).release(release);
    }

    /**
     * Rollback to a release.
     *
     * @param app The app name.
     * @param release The release to which to roll back.
     */
    public RollbackBuilder rollback(String app, String release) {
        return new RollbackBuilder(this).app(app).release(release);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<Release>> {
        private static final String URI = "/apps/:app/releases";

        private ListBuilder(ReleaseService service) {
            super(service, TypeHolder.releaseList(), URI);
        }

        /** The app name. */
        public ListBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
    public static final class InfoBuilder extends HerokuApiBuilder<Release> {
        private static final String URI = "/apps/:app/releases/:release";

        private InfoBuilder(ReleaseService service) {
            super(service, TypeHolder.release(), URI);
        }

        /** The app name. */
        public InfoBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The release name. */
        public InfoBuilder release(String rrelease) {
            field(":release", rrelease);
            return this;
        }
    }
    public static final class RollbackBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/releases";

        private RollbackBuilder(ReleaseService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public RollbackBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The release to which to roll back. */
        public RollbackBuilder release(String release) {
            body("rollback", release);
            return this;
        }
    }
}
