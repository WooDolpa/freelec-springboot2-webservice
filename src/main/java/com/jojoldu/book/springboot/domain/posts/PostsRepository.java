package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName : com.jojoldu.book.springboot.domain.posts
 * className : PostsRepository
 * user : jwlee
 * date : 2022/08/21
 */

/**
 * ibatis나 mybatis 등에서 Dao 로 불리는 DB Layer 접근자이다.
 * JPA 에선 Repository 라고 부르며 인터페이스로 생성함
 * JpaRepository<Entity class, PK 타입> 를 상속하면 기본적인 CURD 메소드가 자동 생성
 * @Repository 를 따로 추가할 필요가 없다.
 * Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
