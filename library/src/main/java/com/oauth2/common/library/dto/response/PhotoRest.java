package com.oauth2.common.library.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhotoRest {
    String userId;
    String photoId;
    String albumId;
    String photoTitle;
    String photoDescription;
    String photoUrl;
}
