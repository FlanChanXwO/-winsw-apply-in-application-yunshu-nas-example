package top.itning.yunshunas.video.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.itning.yunshunas.common.util.MultipartFileSender;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    public PictureController() {}

    @GetMapping("/picture/{path}")
    public void videoForPath(@PathVariable String path, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Path p = Paths.get(path);
        MultipartFileSender.fromPath(p)
                .with(request)
                .with(response)
                .no304CodeReturn()
                .setContentType(Files.probeContentType(p))
                .serveResource();
    }

}
