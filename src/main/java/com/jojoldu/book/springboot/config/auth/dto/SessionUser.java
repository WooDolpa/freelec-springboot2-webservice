package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * packageName : com.jojoldu.book.springboot.config.auth.dto
 * className : SessionUser
 * user : jwlee
 * date : 2022/08/27
 */

/**
 * SessionUser 에는 인증된 정보만 필요함
 * 그 외에 필요한 정보들은 없으므로 name, email, picture 만 필드 선언
 */
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
