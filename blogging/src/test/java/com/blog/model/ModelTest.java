package com.blog.model;

import com.blog.dto.request.PostBlogRequest;
import com.blog.dto.request.PostCommentRequest;
import com.blog.dto.request.RegisterUserRequest;
import com.blog.dto.request.UserAgent;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * Created by rishabh.chowdhary on 12/5/18.
 */
public class ModelTest {
    final String title = "title";
    final String ID = "ID";
    final String desc = "This is a description";
    final String email = "abc@xyz.com";
    final String pwd = "Qwert!234as*";
    final Date date = new Date();
    @Test
    public void testBlog() {
        User u = new User();
        u.setId(ID);
        Blogs b1 = new Blogs();
        b1.setDescription(desc);
        b1.setTitle(title);
        b1.setId(ID);
        b1.setCreatedTime(date);
        b1.setUpdatedTime(date);
        assertNotNull(b1);
        assertEquals(b1.getDescription(),desc);
        assertEquals(b1.getTitle(),title);
        assertEquals(b1.getId(),ID);
        assertEquals(b1.getCreatedTime(),date);
        assertEquals(b1.getUpdatedTime(),date);

        PostBlogRequest request = new PostBlogRequest();
        UserAgent agent = new UserAgent();
        assertEquals(Blogs.create(null,null),null);
        assertEquals(Blogs.create(request,null),null);
        assertEquals(Blogs.create(null,agent),null);
        assertEquals(Blogs.create(request,agent),null);

        request.setDescription(desc);
        request.setTitle(title);
        agent.setLoggedInUser(u);
        b1 = Blogs.create(request,agent);
        assertEquals(b1.getUserId(),ID);
        assertEquals(b1.getTitle(),title);
        assertEquals(b1.getDescription(),desc);
    }
    @Test
    public void testUser() {
        User u = new User();
        u.setName(title);
        u.setEmail(email);
        u.setCreatedTime(date);
        u.setId(ID);
        u.setUpdatedTime(date);
        u.setPassword(pwd);
        assertNotNull(u);
        assertEquals(u.getEmail(),email);
        assertEquals(u.getId(),ID);
        assertEquals(u.getName(),title);
        assertEquals(u.getPassword(),pwd);
        assertEquals(u.getCreatedTime(),date);
        assertEquals(u.getUpdatedTime(),date);
        assertEquals(User.create(null),null);
        assertEquals(User.create(new RegisterUserRequest()),null);
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail(email);
        request.setName(title);
        request.setPassword(pwd);
        u = User.create(request);
        assertEquals(u.getEmail(),email);
        assertEquals(u.getPassword(),pwd);
        assertEquals(u.getName(),title);
        request.setEmail(email.toUpperCase());
        u = User.create(request);
        assertEquals(u.getEmail(),email);

    }
    @Test
    public void testComments() {
        Comments c = new Comments();
        c.setId(ID);
        c.setCreatedTime(date);
        c.setBlogId(ID);
        c.setData(title);
        c.setUserName(email);
        assertNotNull(c);
        assertEquals(c.getCreatedTime(),date);
        assertEquals(c.getId(),ID);
        assertEquals(c.getBlogId(),ID);
        assertEquals(c.getUserName(),email);
        assertEquals(c.getData(),title);
        assertEquals(Comments.create(null,null),null);
        assertEquals(Comments.create(new PostCommentRequest(),null),null);
        assertEquals(Comments.create(null,new UserAgent()),null);
        assertEquals(Comments.create(new PostCommentRequest(),new UserAgent()),null);
        PostCommentRequest request = new PostCommentRequest();
        UserAgent agent = new UserAgent();
        agent.setLoggedInUser(new User());
        assertNotNull((Comments.create(request,agent)));
        request.setBlogId(ID);
        request.setComments(title);
        c = Comments.create(request,agent);
        assertEquals(c.getBlogId(),ID);
        assertEquals(c.getData(),title);

    }
}