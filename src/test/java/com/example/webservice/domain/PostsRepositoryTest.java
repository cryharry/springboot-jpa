package com.example.webservice.domain;

import com.example.webservice.domain.posts.Posts;
import com.example.webservice.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        // 테스트 메소드가 끝날 때 마다 repository 전체 비우는 코드
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given 환경 구축
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 내용")
                .author("테스터")
                .build());

        // when 테스트 선언  // Posts가 DB에 insert
        List<Posts> postsList = postsRepository.findAll();

        //then 결과 검증 // DB insert 조회 및 값 확인
        Posts posts = postsList.get(0);
        //assertThat메서드는 static import -> auto complition이 안 나옴 -> assertThat() 입력 후 alt+Enter
        // is도 마찬가지 -> hamcrest.core.ls.is가 포함되어야 함
        assertThat(posts.getTitle(), is("테스트 게시글"));
        assertThat(posts.getContent(), is("테스트 내용"));
    }
}
