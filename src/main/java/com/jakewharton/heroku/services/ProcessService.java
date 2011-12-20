package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.Process;
import com.jakewharton.heroku.entities.ScaleDynosResponse;
import com.jakewharton.heroku.entities.ScaleWorkersResponse;

public class ProcessService extends HerokuApiService {
    /**
     * List processes for an app.
     *
     * @param app The app name.
     */
    public ListBuilder list(String app) {
        return new ListBuilder(this).app(app);
    }

    /**
     * Run a one-off process.
     *
     * @param app The app name.
     * @param command The command to run.
     */
    public RunBuilder run(String app, String command) {
        return new RunBuilder(this).app(app).command(command);
    }

    /**
     * Restart processes of an app.
     *
     * @param app The app name.
     */
    public RestartBuilder restart(String app) {
        return new RestartBuilder(this).app(app);
    }

    /**
     * Stop processes of an app.
     *
     * @param app The app name.
     */
    public StopBuilder stop(String app) {
        return new StopBuilder(this).app(app);
    }

    /**
     * Scale processes of an app <em>(cedar only)</em>.
     *
     * @param app The app name.
     * @param type The type of process to scale.
     * @param quantity The desired number of processes of this type.
     */
    public ScaleProcessesBuilder scale(String app, String type, int quantity) {
        return new ScaleProcessesBuilder(this).app(app).type(type).quantity(quantity);
    }

    /**
     * Scale dynos of an app <em>(aspen and bamboo only)</em>.
     *
     * @param app The app name.
     * @param quantity The desired number of dynos.
     */
    public ScaleDynosBuilder scaleDynos(String app, int quantity) {
        return new ScaleDynosBuilder(this).app(app).quantity(quantity);
    }

    /**
     * Scale workers of an app <em>(aspen and bamboo only)</em>.
     *
     * @param app The app name.
     * @param quantity The desired number of workers.
     */
    public ScaleWorkersBuilder scaleWorkers(String app, int quantity) {
        return new ScaleWorkersBuilder(this).app(app).quantity(quantity);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<Process>> {
        private static final String URI = "/apps/:app/ps";

        private ListBuilder(ProcessService service) {
            super(service, TypeHolder.processList(), URI);
        }

        /** The app name. */
        public ListBuilder app(String app) {
            field(":app", app);
            return this;
        }
    }
    public static final class RunBuilder extends HerokuApiBuilder<List<Process>> {
        private static final String URI = "/apps/:app/ps";

        private RunBuilder(ProcessService service) {
            super(service, TypeHolder.processList(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public RunBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The command to run. */
        public RunBuilder command(String command) {
            body("command", command);
            return this;
        }
        /** Use rendezvous to access stdin/stdout. */
        public RunBuilder attach(boolean attach) {
            if (attach) {
                body("attach", "true");
            }
            return this;
        }
    }
    public static final class RestartBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/ps/restart";

        private RestartBuilder(ProcessService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public RestartBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The name of a process to restart. */
        public RestartBuilder name(String name) {
            body("ps", name);
            return this;
        }
        /** The type of process to restart. */
        public RestartBuilder type(String type) {
            body("type", type);
            return this;
        }
    }
    public static final class StopBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/ps/stop";

        private StopBuilder(ProcessService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public StopBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The name of a process to stop. */
        public StopBuilder name(String name) {
            body("ps", name);
            return this;
        }
        /** The type of process to stop. */
        public StopBuilder type(String type) {
            body("type", type);
            return this;
        }
    }
    public static final class ScaleProcessesBuilder extends HerokuApiBuilder<String> {
        private static final String URI = "/apps/:app/ps/scale";

        private ScaleProcessesBuilder(ProcessService service) {
            super(service, TypeHolder.string(), URI, HttpMethod.POST);
        }

        /** The app name. */
        public ScaleProcessesBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The type of process to scale. */
        public ScaleProcessesBuilder type(String type) {
            body("type", type);
            return this;
        }
        /** The desired number of processes of this type. */
        public ScaleProcessesBuilder quantity(int quantity) {
            body("qty", Integer.toString(quantity));
            return this;
        }
    }
    public static final class ScaleDynosBuilder extends HerokuApiBuilder<ScaleDynosResponse> {
        private static final String URI = "/apps/:app/dynos";

        private ScaleDynosBuilder(ProcessService service) {
            super(service, TypeHolder.scaleDynosResponse(), URI, HttpMethod.PUT);
        }

        /** The app name. */
        public ScaleDynosBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The desired number of dynos. */
        public ScaleDynosBuilder quantity(int quantity) {
            body("dynos", Integer.toString(quantity));
            return this;
        }
    }
    public static final class ScaleWorkersBuilder extends HerokuApiBuilder<ScaleWorkersResponse> {
        private static final String URI = "/apps/:app/workers";

        private ScaleWorkersBuilder(ProcessService service) {
            super(service, TypeHolder.scaleWorkersResponse(), URI, HttpMethod.PUT);
        }

        /** The app name. */
        public ScaleWorkersBuilder app(String app) {
            field(":app", app);
            return this;
        }
        /** The desired number of workers. */
        public ScaleWorkersBuilder quantity(int quantity) {
            body("workers", Integer.toString(quantity));
            return this;
        }
    }
}
