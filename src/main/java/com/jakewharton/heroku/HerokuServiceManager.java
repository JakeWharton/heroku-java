package com.jakewharton.heroku;

import com.jakewharton.heroku.services.AddonService;
import com.jakewharton.heroku.services.AppService;
import com.jakewharton.heroku.services.CollaboratorService;
import com.jakewharton.heroku.services.ConfigService;
import com.jakewharton.heroku.services.DomainService;
import com.jakewharton.heroku.services.KeyService;
import com.jakewharton.heroku.services.LogService;
import com.jakewharton.heroku.services.ProcessService;
import com.jakewharton.heroku.services.ReleaseService;
import com.jakewharton.heroku.services.StackService;

/**
 * Class to manage service creation with default settings. Meant to be used
 * as a singleton.
 *
 * @author Jake Wharton <jakewharton@gmail.com>
 */
public class HerokuServiceManager {
    /** API key. */
    private String mApiKeyValue;
    /** Connection timeout (in milliseconds). */
    private Integer mConnectionTimeout;
    /** Read timeout (in milliseconds). */
    private Integer mReadTimeout;


    /** Create a new manager instance. */
    public HerokuServiceManager() {}



    /**
     * Set default API key.
     *
     * @param value API key value.
     * @return Current instance for builder pattern.
     */
    public HerokuServiceManager setApiKey(String value) {
        mApiKeyValue = value;
        return this;
    }

    /**
     * Set default connection timeout.
     *
     * @param connectionTimeout Timeout (in milliseconds).
     * @return Current instance for builder pattern.
     */
    public HerokuServiceManager setConnectionTimeout(int connectionTimeout) {
        mConnectionTimeout = connectionTimeout;
        return this;
    }

    /**
     * Set default read timeout.
     *
     * @param readTimeout Timeout (in milliseconds).
     * @return Current instance for builder pattern.
     */
    public HerokuServiceManager setReadTimeout(int readTimeout) {
        mReadTimeout = readTimeout;
        return this;
    }

    /**
     * Set up a new service with the defaults.
     *
     * @param service Service to set up.
     */
    private void setupService(HerokuApiService service) {
        if (mApiKeyValue != null) {
            service.setApiKey(mApiKeyValue);
        }
        if (mConnectionTimeout != null) {
            service.setConnectTimeout(mConnectionTimeout);
        }
        if (mReadTimeout != null) {
            service.setReadTimeout(mReadTimeout);
        }
    }


    public AddonService addons() {
        AddonService service = createAddonService();
        setupService(service);
        return service;
    }
    public AppService apps() {
        AppService service = createAppService();
        setupService(service);
        return service;
    }
    public CollaboratorService colaborators() {
        CollaboratorService service = createColaboratorService();
        setupService(service);
        return service;
    }
    public ConfigService config() {
        ConfigService service = createConfigService();
        setupService(service);
        return service;
    }
    public DomainService domains() {
        DomainService service = createDomainService();
        setupService(service);
        return service;
    }
    public KeyService keys() {
        KeyService service = createKeyService();
        setupService(service);
        return service;
    }
    public LogService logs() {
        LogService service = createLogService();
        setupService(service);
        return service;
    }
    public ProcessService processes() {
        ProcessService service = createProcessService();
        setupService(service);
        return service;
    }
    public ReleaseService releases() {
        ReleaseService service = createReleaseService();
        setupService(service);
        return service;
    }
    public StackService stacks() {
        StackService service = createStackService();
        setupService(service);
        return service;
    }

    public static final AddonService createAddonService() {
        return new AddonService();
    }
    public static final AppService createAppService() {
        return new AppService();
    }
    public static final CollaboratorService createColaboratorService() {
        return new CollaboratorService();
    }
    public static final ConfigService createConfigService() {
        return new ConfigService();
    }
    public static final DomainService createDomainService() {
        return new DomainService();
    }
    public static final KeyService createKeyService() {
        return new KeyService();
    }
    public static final LogService createLogService() {
        return new LogService();
    }
    public static final ProcessService createProcessService() {
        return new ProcessService();
    }
    public static final ReleaseService createReleaseService() {
        return new ReleaseService();
    }
    public static final StackService createStackService() {
        return new StackService();
    }
}
