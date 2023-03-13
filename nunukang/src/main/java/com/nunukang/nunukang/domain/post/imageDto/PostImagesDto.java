package com.nunukang.nunukang.domain.post.imageDto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.post.images.PostImage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class PostImagesDto {
    
    private List<MultipartFile> images;
    

}
