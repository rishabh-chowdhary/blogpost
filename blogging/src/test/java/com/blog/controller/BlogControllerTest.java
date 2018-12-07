package com.blog.controller;

import com.blog.dto.request.PostBlogRequest;
import com.blog.dto.request.UserAgent;
import com.blog.model.Blogs;
import com.blog.model.User;
import com.blog.service.BlogService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)

public class BlogControllerTest {
    private MockMvc mockMvc,mockMvcResolver;
    @InjectMocks
    BlogController blogController;
    @Mock
    BlogService blogService;

    final String title = "title";
    final String ID = "ID";
    final String desc = "This is a description";
    final String email = "abc@xyz.com";
    final String pwd = "Qwert!234as*";
    final Date date = new Date();

    public class StandaloneMvcBlogTestViewResolver extends InternalResourceViewResolver {

        public StandaloneMvcBlogTestViewResolver() {
            super();
        }

        @Override
        protected AbstractUrlBasedView buildView(final String viewName) throws Exception {
            final InternalResourceView view = (InternalResourceView) super.buildView(viewName);
            // prevent checking for circular view paths
            view.setPreventDispatchLoop(false);
            return view;
        }
    }

    @Before
    public void setUp() throws Exception {
        Blogs b1 = new Blogs();
        ArrayList<Blogs> blogs = new ArrayList<>();
        blogs.add(b1);
        MockitoAnnotations.initMocks(this);
        Mockito.when(blogService.getBlogs(BlogController.PAGE_INDEX, BlogController.PAGE_SIZE)).thenReturn(blogs);
        User u = new User();
        u.setPassword(pwd);
        u.setEmail(email);
        u.setName(title);
        PostBlogRequest request = new PostBlogRequest();
        request.setDescription(desc);
        request.setTitle(title);
        UserAgent agent = new UserAgent();
        agent.setLoggedInUser(u);
        Mockito.when(blogService.saveBlog(request,agent)).thenReturn(true);

        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
        mockMvcResolver = MockMvcBuilders.standaloneSetup(blogController).
                setViewResolvers(new StandaloneMvcBlogTestViewResolver()).
                build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("index"));

        mockMvcResolver.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("index"));
    }
    @Test
    public void testBlog() throws Exception {
        //blogPost
        mockMvc.perform(MockMvcRequestBuilders.get("/blog"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));

        mockMvc.perform(MockMvcRequestBuilders.get("/blog")
                                                .sessionAttr("sessionUser",new User()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("blogPost"));
    }
    @Test
    public void testBlogPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/postBlog"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));

        mockMvc.perform(MockMvcRequestBuilders.post("/postBlog")
                                                .sessionAttr("sessionUser",new User()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/postBlog")
                .sessionAttr("sessionUser",new User())
                .param("title",title))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/postBlog")
                .sessionAttr("sessionUser",new User())
                .param("description",desc))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/postBlog")
                .sessionAttr("sessionUser",new User())
                .param("description",desc)
                .param("title",title))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

}