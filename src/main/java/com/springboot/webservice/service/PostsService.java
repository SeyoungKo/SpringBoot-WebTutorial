package com.springboot.webservice.service;

import com.springboot.webservice.domain.posts.Posts;
import com.springboot.webservice.domain.posts.PostsRepository;
import com.springboot.webservice.web.dto.PostsListResponseDto;
import com.springboot.webservice.web.dto.PostsResponseDto;
import com.springboot.webservice.web.dto.PostsSaveRequestDto;
import com.springboot.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // insert (save)
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update
    // 트랜잭션이 끝나는 시점에 테이블에 변경을 반영한다. (Entity 객체 값만 변경하면 별도의 update 쿼리를 날릴 필요가 없다.)
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    // select (find)
    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly=true)
    public List<PostsListResponseDto> findAllDesc(){
        // postsRepository 결과의 stream을 map을 통해 PostsListResponseDto로 변환해 List로 반환
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
