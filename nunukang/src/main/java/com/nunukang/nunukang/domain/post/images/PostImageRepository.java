package com.nunukang.nunukang.domain.post.images;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.nunukang.nunukang.domain.post.Post;;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
 
    List<PostImage> findAllByPostId(Post post);
}
