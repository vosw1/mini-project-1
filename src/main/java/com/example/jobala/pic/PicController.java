package com.example.jobala.pic;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class PicController {

    @Autowired
    private PicRepository picRepository; // 이미지 정보를 저장할 Repository

    @PostMapping("/upload")
    public String upload(PicRequest.UploadDTO requestDTO, HttpServletRequest request, @RequestParam int id) {
        // 1. 데이터 전달 받고
        String title = requestDTO.getTitle();
        MultipartFile imgFile = requestDTO.getImgFile();

        // 2. 파일저장 위치 설정해서 파일을 저장 (UUID 붙여서 롤링)
        String imgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        Path imgPath = Paths.get("./upload/" + imgFilename);
        try {
            Files.write(imgPath, imgFile.getBytes());

            // 3. DB에 저장 (title, realFileName)
            picRepository.upload(title, imgFilename);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 이미지를 업로드하고, 데이터베이스에 저장한 후 해당 이미지의 id를 반환하는 메서드를 호출합니다.
        Pic pic = picRepository.findById(1);
        request.setAttribute("pic", pic);

        return "/guest/resume/detailForm";
    }
}