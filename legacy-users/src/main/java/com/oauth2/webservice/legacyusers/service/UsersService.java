package com.oauth2.webservice.legacyusers.service;

import com.oauth2.common.library.dto.response.UserRest;

public interface UsersService {
    UserRest getUserDetails(String userName, String password);
    UserRest getUserDetails(String userName);
}
