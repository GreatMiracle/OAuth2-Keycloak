package com.oauth2.resource.photo.controller;


import com.oauth2.common.library.dto.response.PhotoRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/photos")
@RestController
public class AlbumsPhotosController {

    @GetMapping(path="/albums/{albumId}")
    public List<PhotoRest> getAlbumPhotos(@PathVariable String albumId) {

        PhotoRest photo1 = PhotoRest.builder()
                .albumId("albumIdHere")
                .photoId("1")
                .userId("1")
                .photoTitle("Photo 1 title")
                .photoDescription("Photo 1 description")
                .photoUrl("Photo 1 URL")
                .build();

        return Arrays.asList(photo1);
    }

}