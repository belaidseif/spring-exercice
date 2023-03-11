package com.rampup.exerciseone.controller;

import com.rampup.exerciseone.dto.CommandDto;
import com.rampup.exerciseone.dto.DetailDto;
import com.rampup.exerciseone.dto.ProductDto;
import com.rampup.exerciseone.service.CommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;


import org.junit.runner.RunWith;

@WebMvcTest(CommandController.class)
@RunWith(SpringRunner.class)
class CommandControllerTest {

    @MockBean private CommandService commandService;

    @Autowired private MockMvc mvc;


    private final CommandDto COMMAND1 =new CommandDto(
            2L,
            ZonedDateTime.parse("2018-10-10T00:00:00+01:00"),
            Arrays.asList(
                    new DetailDto(12, new ProductDto(1L,"PC",129D)),
                    new DetailDto(12, new ProductDto(2L,"Mobile",300D))
            )
    );

    private final CommandDto COMMAND2 =new CommandDto(
            3L,
            ZonedDateTime.parse("2018-10-10T00:00:00+01:00"),
            Arrays.asList(
                    new DetailDto(12, new ProductDto(1L,"PC",129D))
            )
    );

    @Test
    @DisplayName("GET /rampup/api/2/commands should return different commands of client with id 2")
    void getClientCommandsSuccess() throws Exception{
        given(commandService.getClientCommands(2L))
                .willReturn(Arrays.asList(COMMAND1,COMMAND2));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/rampup/api/2/commands");

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].createdAt", is("2018-10-10T00:00:00+01:00")))
                .andExpect(jsonPath("$[0].details.length()", is(2)))
                .andExpect(jsonPath("$[0].details[0].quantity", is(12)))
                .andExpect(jsonPath("$[0].details[0].product.name", is("PC")))
                .andExpect(jsonPath("$[0].details[1]product.price", is(300.0)))
                .andExpect(jsonPath("$[1].id", is(3)))
                .andExpect(jsonPath("$[1].details.length()", is(1)));


    }

    @Test
    void getCommandById() {
    }
}
