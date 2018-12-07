package com.blog.controller;

import com.blog.dto.exceptions.InvalidCredentialException;
import com.blog.dto.request.LoginRequest;
import com.blog.model.User;
import com.blog.service.UserService;
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

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)
/**
 * Created by rishabh.chowdhary on 12/6/18.
 */
public class LoginControllerTest {

    public class StandaloneMvcLoginTestViewResolver extends InternalResourceViewResolver {

        public StandaloneMvcLoginTestViewResolver() {
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

    private MockMvc mockMvc,mockMvcResolver;

    @InjectMocks
    LoginController loginController;
    @Mock
    UserService userService;
    final String title = "title";
    final String ID = "ID";
    final String desc = "This is a description";
    final String email = "abc@xyz.com";
    final String pwd = "Qwert!234as*";
    final Date date = new Date();
    User u;
    LoginRequest request;

    @Before
    public void setUp() throws InvalidCredentialException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userService.find("")).thenReturn(null);
        Mockito.when(userService.find(null)).thenReturn(null);
        u = new User();
        u.setName(title);
        u.setPassword(pwd);
        request = new LoginRequest();
        request.setPassword(u.getPassword());
        request.setUsername(u.getName());
//        request = new RegisterUserRequest();
//        request.setPassword(pwd);
//        request.setEmail(email);
//        request.setName(title);
//        doNothing().when(userService).addUser(request);
//        doNothing().when(userService).addUser(null);
        Mockito.doThrow(new InvalidCredentialException("Invalid login")).when(userService).login(null);
        Mockito.doThrow(new InvalidCredentialException("Invalid login")).when(userService).login(new LoginRequest());
        LoginRequest r = new LoginRequest();
        r.setPassword(pwd);
        Mockito.doThrow(new InvalidCredentialException("Invalid login")).when(userService).login(r);
        LoginRequest q = new LoginRequest();
        q.setUsername(title);
        Mockito.doThrow(new InvalidCredentialException("Invalid login")).when(userService).login(q);
        Mockito.when(userService.login(request)).thenReturn(u);

        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        mockMvcResolver = MockMvcBuilders.standaloneSetup(loginController).
                setViewResolvers(new LoginControllerTest.StandaloneMvcLoginTestViewResolver()).
                build();
    }

    @Test
    public void testLogin() throws Exception {
        mockMvcResolver.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("login"));

        mockMvcResolver.perform(MockMvcRequestBuilders.post("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("login"));

        mockMvcResolver.perform(MockMvcRequestBuilders.post("/login")
                .param("username",title))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("login"));

        mockMvcResolver.perform(MockMvcRequestBuilders.post("/login")
                .param("password",pwd))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(forwardedUrl("login"));

        mockMvcResolver.perform(MockMvcRequestBuilders.post("/login")
                .param("username",title)
                .param("password",pwd))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("index"));


    }


    @Test
    public void testLogout() throws Exception {
        mockMvcResolver.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));
    }

}