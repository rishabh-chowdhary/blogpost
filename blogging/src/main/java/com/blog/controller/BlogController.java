package com.blog.controller;

import com.blog.dto.request.PostBlogRequest;
import com.blog.dto.request.UserAgent;
import com.blog.model.Blogs;
import com.blog.model.User;
import com.blog.service.BlogService;
import com.blog.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
/**
 * This is the controller class for the blogging website's 'index' page.
 * The class also has CRUD functionality for
 *
 */
public class BlogController {

	@Autowired
	private BlogService blogService;
    public static final int PAGE_INDEX = 1;
    public static final int PAGE_SIZE = 50;
    /**
     *
     * @param httpRequest The associated httpservlet request object.
     * @param model
     * @return - Redirects to the user's index page where all blogs present in DB are returned.
     * TODO - Would be useful to split this ask:
     * 1. Create a timeline where only user's owned blogs are displayed.
     * 2. The homepage can have ML models build in(as the blog count goes up)
     * to predict which blogs user would want to interact the most with & showcase those as the primary list.
     * 3. Pagination, or load-as-user-scrolls pagination logic can be a future ask.
     */
	@RequestMapping(path = { "/","/index" })

	public String index(HttpServletRequest httpRequest, Model model) {
//        System.out.println("Session user {}" + SessionUtils.getUser(httpRequest));
//        System.out.println("Model: {}" + model);
		//log.debug("Session user {}", SessionUtils.getUser(httpRequest));
		List<Blogs> blogList = blogService.getBlogs(PAGE_INDEX, PAGE_SIZE);
		model.addAttribute("blogs", blogList);
		return "index";
	}

	@GetMapping("/blog")
	public String blogPost(HttpServletRequest httpRequest) {
		if(SessionUtils.getUser(httpRequest) == null) {
			log.debug("No session, pls login" );
			return "redirect:login";
		}
		return "blogPost";
	}

    /**
     *
     * @param httpRequest - The POST servletrequest
     * @param request - The PostBlogRequest to save the created Blog.
     * @return - Returns to login if no session was found, or otherwise return to index page.
     */
	@PostMapping("/postBlog")
	public String postBlog(HttpServletRequest httpRequest, @Valid PostBlogRequest request, HttpServletResponse response,Model model) {
		User loggedInUser = SessionUtils.getUser(httpRequest);
		if(loggedInUser == null) {
			log.debug("No session, pls login!!" );
			return "redirect:login";
		}
		UserAgent userAgent = new UserAgent();
		userAgent.setLoggedInUser(loggedInUser);
        if(request != null && request.isValid()) {
            blogService.saveBlog(request,userAgent);
        } else {
            model.addAttribute("error","Missing mandatory params");
            return "blog";
        }

		return "redirect:index";
	}

    /**
     *
     * @param model
     * @param id
     * @return Loads the blog page associated with the blog ID.
     * The client can utilize this blogId to make related product calls - CRUD comments, CRUD blog operations, etc.
     */
	@GetMapping("/blogDetails")
	public String blogDetails(Model model, @RequestParam("id") String id) {
		Blogs blogDetails = blogService.getBlogs(id);
		model.addAttribute("blogDetails", blogDetails);
		return "blog";
	}
}
