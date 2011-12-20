package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.Colaborator;

public class CollaboratorService extends HerokuApiService {
    /**
     * List collaborators for an app
     *
     * @param app The app name.
     */
    public ListBuilder list(String app) {
        return new ListBuilder(this).app(app);
    }

    /**
     * Add a collaborator to an app.
     *
     * @param app The app name.
     * @param email The email of the user to add as a collaborator.
     */
    public AddBuilder add(String app, String email) {
        return new AddBuilder(this).app(app).email(email);
    }

    /**
     * Remove a collaborator from an app.
     *
     * @param app The app name.
     * @param email The email of the user to remove as a collaborator.
     */
    public DeleteBuilder delete(String app, String email) {
        return new DeleteBuilder(this).app(app).email(email);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<Colaborator>> {
        private static final String URI = "/apps/:app/collaborators";

        private ListBuilder(CollaboratorService service) {
            super(service, TypeHolder.collaboratorList(), URI);
        }

        /** The app name. */
        public ListBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
    public static final class AddBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/collaborators";

        private AddBuilder(CollaboratorService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public AddBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The email of the user to add as a collaborator. */
        public AddBuilder email(String email) {
            body("collaborator[email]", email);
            return this;
        }
    }
    public static final class DeleteBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/collaborators/:email";

        private DeleteBuilder(CollaboratorService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.DELETE);
        }

        /** The app name. */
        public DeleteBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The email of the user to remove as a collaborator. */
        public DeleteBuilder email(String email) {
            field(":email", email);
            return this;
        }
    }
}
