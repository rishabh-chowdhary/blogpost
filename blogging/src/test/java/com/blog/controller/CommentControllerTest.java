package com.blog.controller;

import com.blog.dto.request.PostCommentRequest;
import com.blog.model.Comments;
import com.blog.model.User;
import com.blog.service.BlogCommentService;
import com.blog.springboot.MainApp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by rishabh.chowdhary on 12/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)



public class CommentControllerTest {

    class CommentJson {
        String status;
        String errorMsg;
        public CommentJson() {

        }
        public CommentJson(String s,String e) {
            this.status = s;
            this.errorMsg = e;
        }
    }

    private MockMvc mockMvc;
    @InjectMocks
    CommentController commentController;
    @Mock
    BlogCommentService blogCommentService;
    PostCommentRequest request;
    CommentJson success,error;

    final String title = "title";
    final String ID = "ID";
    final String desc = "This is a description";
    final String email = "abc@xyz.com";
    User u;
    Comments c;
    ArrayList<Comments> comments;

    @Before
    public void setUp() throws Exception {
        c = new Comments();
        c.setUserName(title);
        c.setBlogId(ID);
        c.setData(desc);
        comments = new ArrayList<>();
        comments.add(c);
        request = new PostCommentRequest();
        request.setBlogId(ID);
        request.setComments(desc);
        MockitoAnnotations.initMocks(this);
        error = new CommentJson("error","User session not found");
        success = new CommentJson("success","Successfully Added");
        Mockito.when(blogCommentService.find("")).thenReturn(null);
        Mockito.when(blogCommentService.find(null)).thenReturn(null);
        ArrayList<Comments> comments = new ArrayList<Comments>();
        comments.add(c);
        Mockito.when(blogCommentService.find(ID)).thenReturn(comments);


        //doNothing().when(userService).addUser(null);
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();

    }

    @Test
    public void testFind() throws Exception {


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/comments/find").param("blogId",ID))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        ObjectMapper mapper = new ObjectMapper();
        List<Comments> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Comments>>() {});
        assertEquals(actual,comments);
    }

    @Test
    public void testSave() throws Exception {
        Gson gson = new Gson();
        mockMvc.perform(MockMvcRequestBuilders.post("/comments/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(error)));

        mockMvc.perform(MockMvcRequestBuilders.post("/comments/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(error)));
                //.andExpect(MockMvcResultMatchers.content().json("{\"status\":\"error\",\"errorMsg\":\"User session not found\"}"));
    }

}