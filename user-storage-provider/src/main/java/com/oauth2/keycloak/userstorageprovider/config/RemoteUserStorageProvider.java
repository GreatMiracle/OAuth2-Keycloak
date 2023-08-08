package com.oauth2.keycloak.userstorageprovider.config;


import com.oauth2.common.library.dto.response.VerifyPasswordResponse;
import com.oauth2.keycloak.userstorageprovider.entity.User;
import com.oauth2.keycloak.userstorageprovider.service.UsersApiService;
import lombok.RequiredArgsConstructor;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

@RequiredArgsConstructor
public class RemoteUserStorageProvider implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {
    private final KeycloakSession session;
    private final ComponentModel model;
    private final UsersApiService usersService;

//    public RemoteUserStorageProvider(KeycloakSession session, ComponentModel model, UsersApiService usersService) {
//        this.session = session;
//        this.model = model;
//        this.usersService = usersService;
//    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String id) {
        StorageId storageId = new StorageId(id);
        String username = storageId.getExternalId();

        return getUserByUsername(realmModel, username);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String username) {
        UserModel returnValue = null;

        User user = usersService.getUserDetails(username);

        if (user != null) {
            returnValue = createUserModel(username, realmModel);
        }

        return returnValue;
    }

    private UserModel createUserModel(String username, RealmModel realm) {
        return new AbstractUserAdapter(session, realm, model) {
            @Override
            public String getUsername() {
                return username;
            }
        };
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String s) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {

        return PasswordCredentialModel.TYPE.equals(credentialType);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        if (!supportsCredentialType(credentialType)) {
            return false;
        }
        return !getCredentialStore().getStoredCredentialsByType(realm, user, credentialType).isEmpty();
    }

    private UserCredentialStore getCredentialStore() {
        return session.userCredentialManager();
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        VerifyPasswordResponse verifyPasswordResponse = usersService.verifyUserPassword(user.getUsername(),
                credentialInput.getChallengeResponse());

        if (verifyPasswordResponse == null)
            return false;

        return verifyPasswordResponse.isResult();
    }






}
