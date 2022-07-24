package by.it.academy.testingTools.common.controllers.user;

import by.it.academy.controllers.user.UserController;
import by.it.academy.entities.user.User;
import by.it.academy.services.user.UsersService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.util.Random;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsersService usersService;
    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Тест получение пользователя с валидным запросом")
    void getUser() throws Exception {
        Random random = new Random();
        long id = random.nextLong();
        User user = new User();
        user.setId(id);
        user.setFirstName("Artsem");
        user.setLastName("Averkov");
        Mockito.when(usersService.getUser(id)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id", Matchers.is(user.getId().toString())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(user.getFirstName()) ))
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    void createUser() throws Exception {
        User user = new User();
        user.setFirstName("Artsem");
        user.setLastName("Averkov");
        Mockito.when(usersService.createUser(user)).thenReturn(user.getId());

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id", Matchers.is(user.getId().toString())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(user.getLastName())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(user.getFirstName())))
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    void deleteUser() throws Exception {
        Random random = new Random();
        long id = random.nextLong();
        User user = new User();
        user.setId(id);
        Mockito.when(usersService.getUser(id)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id", Matchers.is(user.getId().toString())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(user.getLastName())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(user.getFirstName())))
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    void  updateUser() throws Exception {
        Random random = new Random();
        long id = random.nextLong();
        User user = new User();
        user.setFirstName("Artsem");
        user.setLastName("Averkov");
        Mockito.when(usersService.updateUser(user, id));

        mockMvc.perform(MockMvcRequestBuilders.patch("/user/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.id", Matchers.is(user.getId().toString())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(user.getLastName())))
                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(user.getFirstName())))
                .andDo(MockMvcResultHandlers.print());
    }
}
