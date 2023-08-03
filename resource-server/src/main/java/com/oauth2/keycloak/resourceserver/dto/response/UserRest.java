package com.oauth2.keycloak.resourceserver.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRest {
    String userFirstName;
    String userLastName;
    String userId;
}
