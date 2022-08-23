package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * packageName : com.jojoldu.book.springboot.web.dto
 * className : PostsListResponseDto
 * user : jwlee
 * date : 2022/08/24
 */
@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
