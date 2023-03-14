package com.nunukang.nunukang.domain.post;



import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import com.nunukang.nunukang.domain.user.User;



public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByIdDesc();

    List<Post> findByPostWriterOrderByIdDesc(User user);
    
}
