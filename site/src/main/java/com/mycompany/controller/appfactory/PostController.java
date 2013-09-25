package com.mycompany.controller.appfactory;

import com.appfactory.dao.PostDao;
import com.appfactory.domain.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: wli
 * Date: 9/23/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller("postController")
public class PostController {

    @Resource(name = "postDao")
    private PostDao postDao;

    @RequestMapping("/posts/{id}")
    public String showPost(@PathVariable("id") Long id, Model model) {
        Post post = postDao.findById(id);
        if (post != null) {
            model.addAttribute("html", post.getContent());
        }
        return "appfactory/headless";
    }

}
