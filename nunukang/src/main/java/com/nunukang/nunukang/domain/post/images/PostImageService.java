package com.nunukang.nunukang.domain.post.images;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.nunukang.nunukang.domain.post.Post;;

@Service
@RequiredArgsConstructor
public class PostImageService {

    private final PostImageRepository postImageRepository;


    public void savePostImage(PostImage image) {
        try {
            postImageRepository.save(image);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        
        // postImageRepository.save(image);
    }


    public List<PostImage> findByPostWriter(Post post) {
        return postImageRepository.findAllByPostId(post);
    }
}
