package com.rampup.exerciseone.controller;


import com.rampup.exerciseone.ExerciseOneApplication;
import com.rampup.exerciseone.model.Command;
import com.rampup.exerciseone.repository.CommandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ExerciseOneApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CommandControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;



    @Test
    @Sql("/command.sql")
    @DisplayName("should return all commands of client")
    void getCommandByClientIdIntegration() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/rampup/api/2/commands");


//        List<Command> all = repository.findAll();
//        Assertions.assertEquals(all.size(), 9);
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()", is(2)))
//                .andExpect(jsonPath("$[0].id", is(2)))
//                .andExpect(jsonPath("$[0].createdAt", is("2018-10-10T00:00:00+01:00")))
//                .andExpect(jsonPath("$[0].details.length()", is(2)))
//                .andExpect(jsonPath("$[0].details[0].quantity", is(12)))
//                .andExpect(jsonPath("$[0].details[0].product.name", is("PC")))
//                .andExpect(jsonPath("$[0].details[1]product.price", is(300.0)))
//                .andExpect(jsonPath("$[1].id", is(3)))
//                .andExpect(jsonPath("$[1].details.length()", is(1)));
    }

}
