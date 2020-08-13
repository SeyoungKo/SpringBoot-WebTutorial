package com.springboot.webservice.domain.posts;

import javafx.geometry.Pos;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // H2 데이터베이스 자동 실행
public class PostsRepositoryTest {
//    @Autowired
//    PostsRepository postsRepository;
//
//    @After // 단위 테스트가 끝날때마다 수행되는 메소드 (전체 테스트 수행시 데이터 침범을 막기위해 주로 사용됨)
//    public void cleanup(){
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    public void 게시글저장_불러오기(){
//        String title = "테스트 게시글";
//        String content = "테스트 본문";
//
//        // .save() : 테이블에 insert/update 쿼리를 수행 ( id값이 존재하면 update를 수행, 없으면 insert를 수행 )
//        postsRepository.save(Posts.builder()
//                        .title(title)
//                        .content(content)
//                        .author("seyoung")
//                        .build());
//
//        // .findAll() : 테이블에 있는 모든 데이터를 조회하는 메소드
//        List<Posts> postsList = postsRepository.findAll();
//
//        Posts posts = postsList.get(0);
//        assertThat(posts.getTitle()).isEqualTo(title);
//        assertThat(posts.getContent()).isEqualTo(content);
//    }
//
//    // JPA Auditing 테스트
//    @Test
//    public void BaseTimeEntity_등록(){
//        //given
//        LocalDateTime now = LocalDateTime.of(2020,8,1,0,0,0);
//        postsRepository.save(Posts.builder()
//                        .title("title")
//                        .content("content")
//                        .author("author")
//                        .build());
//
//        //when
//        List<Posts> postsList = postsRepository.findAll();
//
//        //then
//        Posts posts = postsList.get(0);
//
//        System.out.println(">>>>>>>>>>> create Date="+posts.getCreatedDate()+", modifiedDate="+posts.getModifiedDate());
//
//        assertThat(posts.getCreatedDate()).isAfter(now);
//        assertThat(posts.getModifiedDate()).isAfter(now);
//    }
@Autowired
PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}