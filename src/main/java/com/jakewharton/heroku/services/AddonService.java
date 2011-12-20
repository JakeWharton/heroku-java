package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.Addon;
import com.jakewharton.heroku.entities.AddonResponse;

public class AddonService extends HerokuApiService {
    /**
     * List all available addons.
     */
    public ListBuilder list() {
        return new ListBuilder(this);
    }

    /**
     * List addons installed on an app.
     *
     * @param app The app name.
     */
    public AppListBuilder list(String app) {
        return new AppListBuilder(this).app(app);
    }

    /**
     * Install an addon to an app.
     *
     * @param app The app name.
     * @param addon The addon name.
     */
    public InstallBuilder install(String app, String addon) {
        return new InstallBuilder(this).app(app).addon(addon);
    }

    /**
     * Upgrade an addon to an app.
     *
     * @param app The app name.
     * @param addon The addon name.
     */
    public UpgradeBuilder upgrade(String app, String addon) {
        return new UpgradeBuilder(this).app(app).addon(addon);
    }

    /**
     * Uninstall an addon from an app.
     *
     * @param app The app name.
     * @param addon The addon name.
     */
    public DeleteBuilder delete(String app, String addon) {
        return new DeleteBuilder(this).app(app).addon(addon);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<Addon>> {
        private static final String URI = "/addons";

        private ListBuilder(AddonService service) {
            super(service, TypeHolder.addonList(), URI);
        }
    }
    public static final class AppListBuilder extends HerokuApiBuilder<List<Addon>> {
        private static final String URI = "/apps/:app/addons";

        private AppListBuilder(AddonService service) {
            super(service, TypeHolder.addonList(), URI);
        }

        /** The app name. */
        public AppListBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
    public static final class InstallBuilder extends HerokuApiBuilder<AddonResponse> {
        private static final String URI = "/apps/:app/addons/:addon";

        private InstallBuilder(AddonService service) {
            super(service, TypeHolder.addonResponse(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public InstallBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The addon name. */
        public InstallBuilder addon(String addon) {
            field(":domain_name", addon);
            return this;
        }
    }
    public static final class UpgradeBuilder extends HerokuApiBuilder<AddonResponse> {
        private static final String URI = "/apps/:app/addons/:addon";

        private UpgradeBuilder(AddonService service) {
            super(service, TypeHolder.addonResponse(), URI, HttpMethod.PUT);
        }

        /** The app name. */
        public UpgradeBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The addon name. */
        public UpgradeBuilder addon(String addon) {
            field(":domain_name", addon);
            return this;
        }
    }
    public static final class DeleteBuilder extends HerokuApiBuilder<AddonResponse> {
        private static final String URI = "/apps/:app/addons/:addon";

        private DeleteBuilder(AddonService service) {
            super(service, TypeHolder.addonResponse(), URI, HttpMethod.DELETE);
        }

        /** The app name. */
        public DeleteBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The addon name. */
        public DeleteBuilder addon(String addon) {
            field(":domain_name", addon);
            return this;
        }
    }
}
