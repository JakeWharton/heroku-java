package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.Domain;

public class DomainService extends HerokuApiService {
    /**
     * List domains for an app
     *
     * @param app The app name.
     */
    public ListBuilder list(String app) {
        return new ListBuilder(this).app(app);
    }

    /**
     * Add a domain to an app.
     *
     * @param app The app name.
     * @param domain The domain to add.
     */
    public AddBuilder add(String app, String domain) {
        return new AddBuilder(this).app(app).domain(domain);
    }

    /**
     * Remove a domain from an app.
     *
     * @param app The app name.
     * @param domain The domain to remove.
     */
    public DeleteBuilder delete(String app, String domain) {
        return new DeleteBuilder(this).app(app).domain(domain);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<Domain>> {
        private static final String URI = "/apps/:app/domains";

        private ListBuilder(DomainService service) {
            super(service, TypeHolder.domainList(), URI);
        }

        /** The app name. */
        public ListBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
    public static final class AddBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/domains";

        private AddBuilder(DomainService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public AddBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The domain to add. */
        public AddBuilder domain(String domain) {
            body("domain_name[domain]", domain);
            return this;
        }
    }
    public static final class DeleteBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/domains/:domain_name";

        private DeleteBuilder(DomainService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.DELETE);
        }

        /** The app name. */
        public DeleteBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The domain to remove. */
        public DeleteBuilder domain(String domain) {
            field(":domain_name", domain);
            return this;
        }
    }
}
