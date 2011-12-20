package com.jakewharton.heroku.services;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;

import com.jakewharton.heroku.entities.Addon;
import com.jakewharton.heroku.entities.AddonResponse;
import com.jakewharton.heroku.entities.App;
import com.jakewharton.heroku.entities.Colaborator;
import com.jakewharton.heroku.entities.Domain;
import com.jakewharton.heroku.entities.Process;
import com.jakewharton.heroku.entities.Release;
import com.jakewharton.heroku.entities.ScaleDynosResponse;
import com.jakewharton.heroku.entities.ScaleWorkersResponse;
import com.jakewharton.heroku.entities.SshKey;
import com.jakewharton.heroku.entities.Stack;

final class TypeHolder {
    private static TypeReference<List<Addon>> ADDON_LIST;
    private static TypeReference<AddonResponse> ADDON_RESPONSE;
    private static TypeReference<App> APP;
    private static TypeReference<List<App>> APP_LIST;
    private static TypeReference<List<Colaborator>> COLABORATOR_LIST;
    private static TypeReference<List<Domain>> DOMAIN_LIST;
    private static TypeReference<Map<String, String>> MAP;
    private static TypeReference<List<Process>> PROCESS_LIST;
    private static TypeReference<Release> RELEASE;
    private static TypeReference<List<Release>> RELEASE_LIST;
    private static TypeReference<ScaleDynosResponse> SCALE_DYNOS_RESPONSE;
    private static TypeReference<ScaleWorkersResponse> SCALE_WORKERS_RESPONSE;
    private static TypeReference<List<SshKey>> SSH_KEY_LIST;
    private static TypeReference<List<Stack>> STACK_LIST;
    private static TypeReference<String> STRING;

    public static TypeReference<List<Addon>> addonList() {
        if (ADDON_LIST == null) {
            ADDON_LIST = new TypeReference<List<Addon>>() {};
        }
        return ADDON_LIST;
    }
    public static TypeReference<AddonResponse> addonResponse() {
        if (ADDON_RESPONSE == null) {
            ADDON_RESPONSE = new TypeReference<AddonResponse>() {};
        }
        return ADDON_RESPONSE;
    }
    public static TypeReference<App> app() {
        if (APP == null) {
            APP = new TypeReference<App>() {};
        }
        return APP;
    }
    public static TypeReference<List<App>> appList() {
        if (APP_LIST == null) {
            APP_LIST = new TypeReference<List<App>>() {};
        }
        return APP_LIST;
    }
    public static TypeReference<List<Domain>> domainList() {
        if (DOMAIN_LIST == null) {
            DOMAIN_LIST = new TypeReference<List<Domain>>() {};
        }
        return DOMAIN_LIST;
    }
    public static TypeReference<List<Process>> processList() {
        if (PROCESS_LIST == null) {
            PROCESS_LIST = new TypeReference<List<Process>>() {};
        }
        return PROCESS_LIST;
    }
    public static TypeReference<List<Colaborator>> collaboratorList() {
        if (COLABORATOR_LIST == null) {
            COLABORATOR_LIST = new TypeReference<List<Colaborator>>() {};
        }
        return COLABORATOR_LIST;
    }
    public static TypeReference<Map<String, String>> map() {
        if (MAP == null) {
            MAP = new TypeReference<Map<String, String>>() {};
        }
        return MAP;
    }
    public static TypeReference<Release> release() {
        if (RELEASE == null) {
            RELEASE = new TypeReference<Release>() {};
        }
        return RELEASE;
    }
    public static TypeReference<List<Release>> releaseList() {
        if (RELEASE_LIST == null) {
            RELEASE_LIST = new TypeReference<List<Release>>() {};
        }
        return RELEASE_LIST;
    }
    public static TypeReference<ScaleDynosResponse> scaleDynosResponse() {
        if (SCALE_DYNOS_RESPONSE == null) {
            SCALE_DYNOS_RESPONSE = new TypeReference<ScaleDynosResponse>() {};
        }
        return SCALE_DYNOS_RESPONSE;
    }
    public static TypeReference<ScaleWorkersResponse> scaleWorkersResponse() {
        if (SCALE_WORKERS_RESPONSE == null) {
            SCALE_WORKERS_RESPONSE = new TypeReference<ScaleWorkersResponse>() {};
        }
        return SCALE_WORKERS_RESPONSE;
    }
    public static TypeReference<List<SshKey>> sshKeyList() {
        if (SSH_KEY_LIST == null) {
            SSH_KEY_LIST = new TypeReference<List<SshKey>>() {};
        }
        return SSH_KEY_LIST;
    }
    public static TypeReference<List<Stack>> stackList() {
        if (STACK_LIST == null) {
            STACK_LIST = new TypeReference<List<Stack>>() {};
        }
        return STACK_LIST;
    }
    public static TypeReference<String> string() {
        if (STRING == null) {
            STRING = new TypeReference<String>() {};
        }
        return STRING;
    }
}
