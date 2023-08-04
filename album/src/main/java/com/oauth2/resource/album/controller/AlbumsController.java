package com.oauth2.resource.album.controller;

import com.oauth2.common.library.dto.response.AlbumRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    @GetMapping
    public List<AlbumRest> getAlbums() {

        AlbumRest album1 = AlbumRest.builder()
                .albumId("albumIdHere")
                .userId("1")
                .albumTitle("Album 1 title")
                .albumDescription("Album 1 description")
                .albumUrl("Album 1 URL")
                .build();

        AlbumRest album2 = album1.toBuilder()
                .albumId("2")
                .albumTitle("Album 2 title")
                .albumDescription("Album 2 description")
                .albumUrl("Album 2 URL")
                .build();

        return Arrays.asList(album1, album2);
    }

}
