package com.jakewharton.heroku.services;

import java.net.HttpURLConnection;
import java.util.List;
import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.App;

public class AppService extends HerokuApiService {
    /**
     * List apps.
     */
    public ListBuilder list() {
        return new ListBuilder(this);
    }

    /**
     * Get info for an app.
     *
     * @param name The app name.
     */
    public InfoBuilder info(String name) {
        return new InfoBuilder(this).name(name);
    }

    /**
     * Create an app.
     *
     * @param name The app name.
     * @param stack The stack.
     */
    public CreateBuilder create(String name, String stack) {
        return new CreateBuilder(this).name(name).stack(stack);
    }

    /**
     * Rename an app.
     *
     * @param name The original app name.
     * @param newName The new app name.
     */
    public RenameBuilder rename(String name, String newName) {
        return new RenameBuilder(this).name(name).newName(newName);
    }

    /**
     * Transfer an app.
     *
     * @param name The app name.
     * @param owner The new app owner.
     * @return
     */
    public TransferBuilder transfer(String name, String owner) {
        return new TransferBuilder(this).name(name).owner(owner);
    }

    /**
     * Toggle maintenance mode for an app.
     *
     * @param name The app name.
     * @param value On or off.
     */
    public ToggleMaintenanceBuilder toggleMaintenance(String name, boolean value) {
        return new ToggleMaintenanceBuilder(this).name(name).value(value);
    }

    /**
     * Destroy an app.
     *
     * @param name The app name.
     */
    public DeleteBuilder delete(String name) {
        return new DeleteBuilder(this).name(name);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<App>> {
        private static final String URI = "/apps";

        private ListBuilder(AppService service) {
            super(service, TypeHolder.appList(), URI);
        }
    }
    public static final class InfoBuilder extends HerokuApiBuilder<App> {
        private static final String URI = "/apps/:name";

        private InfoBuilder(AppService service) {
            super(service, TypeHolder.app(), URI);
        }

        /** The app name. */
        public InfoBuilder name(String name) {
            field(":name", name);
            return this;
        }
    }
    public static final class CreateBuilder extends HerokuApiBuilder<App> {
        private static final String URI = "/apps";

        private CreateBuilder(AppService service) {
            super(service, TypeHolder.app(), URI, HttpMethod.PUT, HttpURLConnection.HTTP_ACCEPTED);
        }

        /** The app name. */
        public CreateBuilder name(String name) {
            body("app[name]", name);
            return this;
        }
        /** The stack. */
        public CreateBuilder stack(String stack) {
            body("app[stack]", stack);
            return this;
        }
    }
    public static final class RenameBuilder extends HerokuApiBuilder<Void> {
        private static final String URI = "/apps/:name";

        private RenameBuilder(AppService service) {
            super(service, null, URI, HttpMethod.PUT);
        }

        /** The app name. */
        public RenameBuilder name(String name) {
            field(":name", name);
            return this;
        }
        /** The app name. */
        public RenameBuilder newName(String newName) {
            body("app[name]", newName);
            return this;
        }
    }
    public static final class TransferBuilder extends HerokuApiBuilder<Void> {
        private static final String URI = "/apps/:name";

        private TransferBuilder(AppService service) {
            super(service, null, URI, HttpMethod.PUT);
        }

        /** The app name. */
        public TransferBuilder name(String name) {
            field(":name", name);
            return this;
        }
        /** The new app owner. */
        public TransferBuilder owner(String owner) {
            body("app[transfer_owner]", owner);
            return this;
        }
    }
    public static final class ToggleMaintenanceBuilder extends HerokuApiBuilder<Void> {
        private static final String URI = "/apps/:app/server/maintenance";

        private ToggleMaintenanceBuilder(AppService service) {
            super(service, null, URI, HttpMethod.POST);
        }

        /** The app name. */
        public ToggleMaintenanceBuilder name(String name) {
            field(":app", name);
            return this;
        }
        /** On or off. */
        public ToggleMaintenanceBuilder value(boolean value) {
            body("maintenance_mode", value ? "1" : "0");
            return this;
        }
    }
    public static final class DeleteBuilder extends HerokuApiBuilder<Void> {
        private static final String URI = "/apps/:name";

        private DeleteBuilder(AppService service) {
            super(service, null, URI, HttpMethod.DELETE);
        }

        /** The app name. */
        public DeleteBuilder name(String name) {
            field(":name", name);
            return this;
        }
    }
}
