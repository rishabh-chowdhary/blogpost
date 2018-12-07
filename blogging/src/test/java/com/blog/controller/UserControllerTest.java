package com.blog.controller;

import com.blog.dto.exceptions.UserAlreadyExistException;
import com.blog.dto.request.RegisterUserRequest;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)
/**
 * Created by rishabh.chowdhary on 12/5/18.
 */
public class UserControllerTest {

    private MockMvc mockMvc, mockMvcResolver;

    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;

    final String title = "title";
    final String ID = "ID";
    final String desc = "This is a description";
    final String email = "abc@xyz.com";
    final String reg_email = "rc@test.com";
    final String pwd = "Qwert!234as*";
    final Date date = new Date();
    User u, reg_user;
    RegisterUserRequest request, userAlreadyExistRequest;

    public class StandaloneMvcUserTestViewResolver extends InternalResourceViewResolver {

        public StandaloneMvcUserTestViewResolver() {
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

        MockitoAnnotations.initMocks(this);

        u = new User();
        u.setEmail(email);
        u.setName(title);
        u.setName(title);
        request = new RegisterUserRequest();
        request.setPassword(pwd);
        request.setEmail(email);
        request.setName(title);

        reg_user = new User();
        reg_user.setEmail(reg_email);
        reg_user.setName(title);
        reg_user.setPassword(pwd);

        userAlreadyExistRequest = new RegisterUserRequest();
        userAlreadyExistRequest.setEmail(reg_user.getEmail());
        userAlreadyExistRequest.setName(reg_user.getName());
        userAlreadyExistRequest.setPassword(reg_user.getPassword());


        Mockito.when(userService.find("")).thenReturn(null);
        Mockito.when(userService.find(email)).thenReturn(null);
        Mockito.when(userService.find(reg_email)).thenReturn(reg_user);

        Mockito.when(userService.addUser(org.mockito.Matchers.anyObject())).then(new Answer<Boolean>() {

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                RegisterUserRequest userRequest = (RegisterUserRequest)invocation.getArguments()[0];
                if(userRequest.getEmail().equals(userAlreadyExistRequest.getEmail())) {
                    throw new UserAlreadyExistException("User already exists!");
                }
                System.out.println(invocation.getArguments());
                return true;
            }
        });



		/*doThrow(new UserAlreadyExistException("User already exists!")).when(userService).addUser(userAlreadyExistRequest);
		doNothing().when(userService).addUser(request);
		doNothing().when(userService).addUser(null);
		*/
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        mockMvcResolver = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(new StandaloneMvcUserTestViewResolver()).build();
    }

    @Test
    public void testRegistration() throws Exception {
        mockMvcResolver.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(forwardedUrl("registration"));
    }

    @Test
    public void testAddUser() throws Exception {
        mockMvcResolver
                .perform(MockMvcRequestBuilders.post("/users/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", request.getEmail())
                        .param("password", request.getPassword())
                        .param("name", request.getName())
                )
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(forwardedUrl("login"));

    }

    @Test
    public void testUserAlreadyExist() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/users/save").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", userAlreadyExistRequest.getEmail())
                .param("password", userAlreadyExistRequest.getPassword())
                .param("name", userAlreadyExistRequest.getName())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.request().attribute("error", is("User Already exists")))
                .andExpect(forwardedUrl("registration"));

        ;
    }

    // @Test
    // public void testFindUser() throws Exception {
    // Gson gson = new Gson();
    // mockMvcResolver.perform(MockMvcRequestBuilders.get("/users/find")
    // .param("email",u.getEmail()))
    // .andDo(print())
    // .andExpect(MockMvcResultMatchers.status().isOk())
    // .andExpect(MockMvcResultMatchers.model().);
    //
    // }
    //
    // @Test
    // public void delete() throws Exception {
    //
    // }

}