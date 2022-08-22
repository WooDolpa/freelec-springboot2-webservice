package com.jojoldu.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName : com.jojoldu.book.springboot.web
 * className : IndexController
 * user : jwlee
 * date : 2022/08/22
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    /**
     * mustache Starter 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨
     * 앞의 경로 /src/main/resources/templates
     * 뒤의 경로 .mustache
     * 즉 src/main/resources/index.mustache 로 전환되어 View Resolver 가 처리함
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

}
