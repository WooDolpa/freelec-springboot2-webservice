package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * packageName : com.jojoldu.book.springboot.web
 * className : IndexController
 * user : jwlee
 * date : 2022/08/22
 */
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    /**
     * mustache Starter 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨
     * 앞의 경로 /src/main/resources/templates
     * 뒤의 경로 .mustache
     * 즉 src/main/resources/index.mustache 로 전환되어 View Resolver 가 처리함
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("posts", postsService.findAllDesc());

        /**
         * CustomOAuth2UserService 에서 로그인 성공시 세션 SessionUser 를 저장하도록 구성
         * 로그인 성공시 httpSession.getAttribute("user") 에서 값을 가져올 수 있음
         */
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate (@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
