package com.oauth2.common.library.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    long id;
    String userId;
    String firstName;
    String lastName;
    String email;
    String encryptedPassword;
    String emailVerificationToken;
    Boolean emailVerificationStatus;
}
