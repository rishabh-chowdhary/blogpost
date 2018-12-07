package com.blog.controller;

import com.blog.dto.request.PostCommentRequest;
import com.blog.dto.request.Response;
import com.blog.dto.request.UserAgent;
import com.blog.model.Comments;
import com.blog.model.User;
import com.blog.service.BlogCommentService;
import com.blog.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
/**
 * This class is used to manage the  CRUD functionality of comments for a given blog.
 * As the feature set of a blog grows, i.e. to have likes, shares, engagement metrics as well ,
 * the framework can be extended to add more CRUD logic based on the feature set.
 */
public class CommentController {

	@Autowired
	private BlogCommentService blogCommentService;

    /**
     *
     * @param blogId - The associated blogId for which the comments have to be returned.
     * @return - An array of dictionary objects representing user comments on the given blog.
     */
	@GetMapping("/comments/find")
	public List<Comments> find(@RequestParam String blogId) {
		return blogCommentService.find(blogId);
	}

    /**
     *
     * @param httpRequest - The httpservlet POST request.
     * @param request The request object containing the details about the comment object.
     * @return - Returns a response with a success/error msg.
     * The client must be able to parse this & showcase the necessary UI alerts,
     * and upon a succesful add redirect the user to login page if required.
     */
	@PostMapping("/comments/save")
	public Response save(HttpServletRequest httpRequest, @Valid PostCommentRequest request) {
		User loggedInUser = SessionUtils.getUser(httpRequest);
		Response response = null;
        log.debug("Logged In User:" + loggedInUser);
		if(loggedInUser == null) {
			log.debug("No session, pls login!!" );
            //TODO - Check if throwing a bad request here would be a better scenario
			response = new Response("error", "User session not found");
		}
		else {
            if(request != null & request.isValid()) {
                UserAgent userAgent = new UserAgent();
                userAgent.setLoggedInUser(loggedInUser);
                blogCommentService.save(request, userAgent);
                //TODO - Check if the comment was duplicated & handle errors.
                response = new Response("success", "Successfully Added");
            } else {
                response = new Response("error", "Please provide valid values.");
            }

		}
		return response;
	}

}
