package com.nunukang.nunukang.domain.post.imageDto;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.post.images.PostImage;
import com.nunukang.nunukang.domain.post.images.PostImageService;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostImagesDtoService {

    private final PostImageService postImageService;
    
    @Value("${postPicturePath}")
    // postPicturePath: file:///Users/sim/Nunukang/Post/
    private String thymeleafUsePostPicturePath;

    @Transactional
    // 로컬 서버에 이미지 저장 및 인자로 받은 Post 객체와 연관관계 설정
    public void saveImages(Post post, PostImagesDto postImagesDto, String defaultPostImageSavePath) throws IOException {
        String postPath = defaultPostImageSavePath + post.getId();

        // 로컬 서버에 이미지 저장을 위한 디렉토리 생성
        String postPicturesPath = createDirectory(postPath);
        
        int cnt = 0;
        for (MultipartFile image : postImagesDto.getImages()) {
            String imagePath = postPicturesPath + "/" + ++cnt + ".png";
            
            // String imagePath = postPicturesPath + "/" + image.getOriginalFilename();

            // images의 image를 PostImage 객체로 만들고 인자로 받은 Post 객체와 연관관계를 설정해준다.
            PostImage tmp = new PostImage(post, cnt + ".png", thymeleafUsePostPicturePath);
            post.getImages().add(tmp);

            // postImage 객체 저장
            postImageService.savePostImage(tmp);


            // 이미지 저장
            Path imgPath = Paths.get(imagePath);
            image.transferTo(imgPath);

        }
    }

    public String createDirectory(String postPath) {
        File postIdDirectory = new File(postPath);
        if (!postIdDirectory.exists()) {
            postIdDirectory.mkdir();

            File pictureDirectory = new File(postPath + "/Pictures");
            pictureDirectory.mkdir();

            return postPath + "/Pictures";
        } else {
            return postPath + "/Pictures";
        }
    }
}
