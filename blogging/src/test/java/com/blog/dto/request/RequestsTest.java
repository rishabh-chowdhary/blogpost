package com.blog.dto.request;

import com.blog.model.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * Created by rishabh.chowdhary on 12/5/18.
 */
public class RequestsTest {
    final String title = "title";
    final String ID = "ID";
    final String desc = "This is a description";
    final String email = "abc@xyz.com";
    final String pwd = "Qwert!234as*";
    final Date date = new Date();
    @Test
    public void testLoginRequest() {
        LoginRequest request = new LoginRequest();
        request.setPassword(pwd);
        request.setUsername(title);
        assertNotNull(request);
        assertEquals(request.getPassword(),pwd);
        assertEquals(request.getUsername(),title);
    }
    @Test
    public void testPostBlogRequest() {
        PostBlogRequest request = new PostBlogRequest();
        request.setTitle(title);
        request.setDescription(desc);
        assertNotNull(request);
        assertEquals(request.getTitle(),title);
        assertEquals(request.getDescription(),desc);
    }
    @Test
    public void testPostCommentRequest() {
        PostCommentRequest request = new PostCommentRequest();
        request.setComments(desc);
        request.setBlogId(ID);
        assertNotNull(request);
        assertEquals(request.getBlogId(),ID);
        assertEquals(request.getComments(),desc);
    }
    @Test
    public void testPostCommentsResponse() {
        Response response = new Response();
        response.setErrorMsg(desc);
        response.setStatus(title);
        assertNotNull(response);
        assertEquals(response.getStatus(),title);
        assertEquals(response.getErrorMsg(),desc);
    }
    @Test
    public void testRegisterUserRequest() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail(email);
        request.setName(title);
        request.setPassword(pwd);
        assertNotNull(request);
        assertEquals(request.getEmail(),email);
        assertEquals(request.getPassword(),pwd);
        assertEquals(request.getName(),title);

    }
    @Test
    public void testUserAgent() {
        User u = new User();
        u.setId(ID);
        UserAgent agent = new UserAgent();
        agent.setLoggedInUser(null);
        assertNotNull(agent);
        assertEquals(agent.getLoggedInUser(),null);
        agent.setLoggedInUser(u);
        assertEquals(agent.getLoggedInUser(),u);
    }
}