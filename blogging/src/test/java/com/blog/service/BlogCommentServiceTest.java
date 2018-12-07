package com.blog.service;

import com.blog.model.Comments;
import com.blog.repository.CommentRepository;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rishabh.chowdhary on 12/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)

public class BlogCommentServiceTest {

    @InjectMocks
    BlogCommentService commentService;
    @Mock
    CommentRepository commentRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(commentRepository.findByBlogId(null)).thenReturn(null);
        Mockito.when(commentRepository.findByBlogId("")).thenReturn(null);
        Mockito.when(commentRepository.findByBlogId("abcd")).thenReturn(null);
        Mockito.when(commentRepository.findByBlogId("5c04d017e2ed4643d52fe52f")).thenReturn(new ArrayList<Comments>());
    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void find() throws Exception {
        List<Comments> comments = commentService.find(null);
        assertEquals(comments,null);
        comments = commentService.find("");
        assertEquals(comments,null);
        comments = commentService.find("5c04d017e2ed4643d52fe52f");
        assertNotNull(comments);
    }

}