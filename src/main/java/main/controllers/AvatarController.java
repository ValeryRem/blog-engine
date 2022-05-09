package main.controllers;

import main.view.service.GetService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
// искусственный коммит 9.5.22
@RestController
@RequestMapping("/{folder}/{dir1}/{dir2}/{dir3}/{filename}")
public class AvatarController {

    private final GetService getService;

    public AvatarController(GetService getService) {
        this.getService = getService;
    }

    @GetMapping("")
    @ResponseBody
    private HttpEntity<byte[]> getPhoto(
            @PathVariable("folder") String folder,
            @PathVariable("dir1") String dir1,
            @PathVariable("dir2") String dir2,
            @PathVariable("dir3") String dir3,
            @PathVariable("filename") String filename) throws IOException {
        return getService.getPhoto(folder, dir1, dir2, dir3, filename);
    }
}
