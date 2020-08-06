package com.springboot.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Entity 클래스와 Entity Repository는 같은 위치에 있어야 한다. (함께 관리)
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // findAll (SpringDataJPA에서 제공하지 않는 메소드는 쿼리로 작성해도 된다.)
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
