package com.blog.service;

import com.blog.dto.request.PostBlogRequest;
import com.blog.dto.request.UserAgent;
import com.blog.model.Blogs;
import com.blog.model.User;
import com.blog.repository.BlogRepository;
import com.blog.springboot.MainApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by rishabh.chowdhary on 12/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)



public class BlogServiceTest {

    @InjectMocks
    BlogService blogService;
    @Mock
    BlogRepository blogsRepository;

    Blogs blog;
    UserAgent userAgent,userAgent2;
    PostBlogRequest request;
    Blogs blog2,blog3;
    final String ID = "abcd";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userAgent = new UserAgent();
        userAgent2 = new UserAgent();
        userAgent2.setLoggedInUser(new User());
        request = new PostBlogRequest();
        blog2 = Blogs.create(request,userAgent);
        blog3 = Blogs.create(request,userAgent2);
        blog = new Blogs();
        blog.setId(ID);
        blog.setTitle("title");
        blog.setDescription("this is a description");
        ArrayList<Blogs> blogs = new ArrayList<Blogs>();
        blogs.add(blog);
        Mockito.when(blogsRepository.findAll()).thenReturn(blogs);
        Mockito.when(blogsRepository.findOne("")).thenReturn(null);
        Mockito.when(blogsRepository.findOne(ID)).thenReturn(blog);
        Mockito.when(blogsRepository.save(blog)).thenReturn(blog);
        Mockito.when(blogsRepository.save(blog2)).thenReturn(blog2);
        Mockito.when(blogsRepository.save(blog3)).thenReturn(blog3);

    }

    @Test
    public void getBlogs() throws Exception {
        ArrayList<Blogs> blogs = (ArrayList<Blogs>) blogService.getBlogs(1,5);
        assertNotNull(blogs);
        assertTrue(blogs.size() > 0);

        blogs = (ArrayList<Blogs>) blogService.getBlogs(3,50);
        assertNotNull(blogs);
        assertTrue(blogs.size() > 0);

        blogs = (ArrayList<Blogs>) blogService.getBlogs(10,5);
        assertNotNull(blogs);
        assertTrue(blogs.size() > 0);

        Blogs b = blogService.getBlogs("");
        assertEquals(b,null);
        b = blogService.getBlogs(ID);
        assertEquals(b,blog);
        b = blogService.getBlogs(null);
        assertEquals(b,null);
    }
    @Test
    public void saveBlog() throws Exception {
        boolean success =false;
        success = blogService.saveBlog(request,userAgent);
        assertEquals(success,true);
        success = blogService.saveBlog(request,userAgent2);
        assertEquals(success,true);
        success = blogService.saveBlog(null,null);
        assertEquals(success,false);
        success = blogService.saveBlog(null,new UserAgent());
        assertEquals(success,false);
        success = blogService.saveBlog(new PostBlogRequest(),null);
        assertEquals(success,false);

    }

}