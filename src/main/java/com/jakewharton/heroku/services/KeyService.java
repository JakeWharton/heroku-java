package com.jakewharton.heroku.services;

import java.util.List;

import com.jakewharton.heroku.HerokuApiBuilder;
import com.jakewharton.heroku.HerokuApiService;
import com.jakewharton.heroku.entities.SshKey;

public class KeyService extends HerokuApiService {
    /**
     * List SSH keys.
     */
    public ListBuilder list() {
        return new ListBuilder(this);
    }

    /**
     * Associate an SSH key with this account.
     *
     * @param key The SSH key.
     */
    public AssociateBuilder associate(String key) {
        return new AssociateBuilder(this).key(key);
    }

    /**
     * Remove all SSH keys from this account.
     */
    public DeleteAllBuilder delete() {
        return new DeleteAllBuilder(this);
    }

    /**
     * Remove an SSH key from this account.
     *
     * @param key The username@hostname description field of the key.
     */
    public DeleteBuilder delete(String key) {
        return new DeleteBuilder(this).key(key);
    }


    public static final class ListBuilder extends HerokuApiBuilder<List<SshKey>> {
        private static final String URI = "/user/keys";

        private ListBuilder(KeyService service) {
            super(service, TypeHolder.sshKeyList(), URI);
        }
    }
    public static final class AssociateBuilder extends HerokuApiBuilder<Void> {
        private static final String URI = "/user/keys";

        private AssociateBuilder(KeyService service) {
            super(service, null, URI, HttpMethod.POST);
        }

        /** The SSH key. */
        public AssociateBuilder key(String key) {
            body("body", key);
            return this;
        }
    }
    public static final class DeleteAllBuilder extends HerokuApiBuilder<Void> {
        private static final String URI = "/user/keys";

        private DeleteAllBuilder(KeyService service) {
            super(service, null, URI, HttpMethod.DELETE);
        }
    }
    public static final class DeleteBuilder extends HerokuApiBuilder<Void> {
        private static final String URI = "/user/keys/:key";

        private DeleteBuilder(KeyService service) {
            super(service, null, URI, HttpMethod.DELETE);
        }

        /** The username@hostname description field of the key. */
        public DeleteBuilder key(String key) {
            field(":key", key);
            return this;
        }
    }
}
