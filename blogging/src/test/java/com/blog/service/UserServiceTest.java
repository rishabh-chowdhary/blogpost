package com.blog.service;

import com.blog.dto.exceptions.UserAlreadyExistException;
import com.blog.dto.exceptions.UserNotExistException;
import com.blog.model.User;
import com.blog.repository.UserRepository;
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

import static org.junit.Assert.assertEquals;

/**
 * Created by rishabh.chowdhary on 12/5/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApp.class)
//@WebMvcTest(controllers = { BlogController.class }, secure = false)
//@AutoConfigureDataMongo

public class UserServiceTest {


    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    User rc,np;
    @Before
    public void setUp() throws UserAlreadyExistException,UserNotExistException {
        MockitoAnnotations.initMocks(this);
        rc = new User();
        rc.setEmail("rc@test.com");
        rc.setName("RC");

        np = new User();
        np.setEmail("np@test.com");
        np.setName("NP");
//        RegisterUserRequest request = new RegisterUserRequest();
//        request.setName(np.getName());
//        request.setEmail(np.getEmail());
//        RegisterUserRequest request2 = new RegisterUserRequest();
//        request.setName(rc.getName());
//        request.setEmail(rc.getEmail());
//
//        Mockito.when(userService.find(rc.getEmail())).thenReturn(rc);
//        Mockito.when(userService.find("")).thenReturn(null);
//        Mockito.when(userService.getLoggedInUser()).thenReturn(rc);
//        Mockito.when(userService.deleteByEmail(rc.getEmail())).thenReturn(rc);
//        Mockito.doThrow(new UserNotExistException("")).when(userService).deleteByEmail(null);
//        Mockito.doThrow(new UserAlreadyExistException("")).when(userService).addUser(request);
//        doNothing().when(userService).addUser(request);
//
//        userService.addUser(request);
        Mockito.when(userRepository.findByEmail(rc.getEmail())).thenReturn(rc);
        Mockito.when(userRepository.findByEmail(np.getEmail())).thenReturn(np);
        Mockito.when(userRepository.findByEmail("")).thenReturn(null);
        Mockito.when(userRepository.findByEmail(null)).thenReturn(null);
        Mockito.when(userRepository.findByEmail("xyz@abc.com")).thenReturn(null);

    }

    @Test
    public void find() throws Exception {
        User u = userService.find("rc@test.com");
        assertEquals(rc,u);
        u = userService.find("");
        assertEquals(u,null);
        u = userService.find("xyz@abc.com");
        assertEquals(u,null);


    }

}