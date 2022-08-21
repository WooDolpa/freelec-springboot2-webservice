package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * packageName : com.jojoldu.book.springboot.domain.posts
 * className : BaseTimeEntity
 * user : jwlee
 * date : 2022/08/21
 */

@Getter
/**
 * JPA Entity 클래스들이 BaseTimeEntity 을 상속할 경우 필드들도 컬럼에 인식하도록 한다.
 */
@MappedSuperclass
/**
 * BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
 */
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // Entity 가 생성되어 저장될 때 시간이 자동 저장
    @CreatedDate
    private LocalDateTime createDate;

    // 조회한 Entity 의 값을 변경할 때 시간이 자동 저장됨
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
