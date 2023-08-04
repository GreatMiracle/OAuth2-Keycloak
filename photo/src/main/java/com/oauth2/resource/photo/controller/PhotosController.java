package com.oauth2.resource.photo.controller;


import com.oauth2.common.library.dto.response.PhotoRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotosController {

    @GetMapping
    public List<PhotoRest> getPhotos() {

        PhotoRest photo1 = PhotoRest.builder()
                .albumId("albumIdHere")
                .photoId("1")
                .userId("1")
                .photoTitle("Photo 1 title")
                .photoDescription("Photo 1 description")
                .photoUrl("Photo 1 URL")
                .build();

        PhotoRest photo2 = photo1.toBuilder()
                .photoId("2")
                .photoTitle("Photo 2 title")
                .photoDescription("Photo 2 description")
                .photoUrl("Photo 2 URL")
                .build();

        return Arrays.asList(photo1, photo2);
    }

}
