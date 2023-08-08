package com.oauth2.keycloak.userstorageprovider.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
     String firstName;
     String lastName;
     String email;
     String userName;
     String userId;

}
