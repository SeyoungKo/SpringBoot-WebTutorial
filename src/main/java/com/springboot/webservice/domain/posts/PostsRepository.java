package com.springboot.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Entity 클래스와 Entity Repository는 같은 위치에 있어야 한다. (함께 관리)
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
