package com.oauth2.common.library.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlbumRest {
    String userId;
    String albumId;
    String albumTitle;
    String albumUrl;
    String albumDescription;
    List<PhotoRest> photos;
}
