package com.jojoldu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * packageName : com.jojoldu.book.springboot.config
 * className : JpaConfig
 * user : jwlee
 * date : 2022/08/30
 */
@Configuration
@EnableJpaAuditing      // JPA Auditing 활성화
public class JpaConfig {

}
