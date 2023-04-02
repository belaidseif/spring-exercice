package com.rampup.exerciseone.repository;

import com.rampup.exerciseone.model.Command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:app-test.properties")
class CommandRepositoryTest {

    @Autowired private CommandRepository repository;



    @Test
    @Sql("/command.sql")
    @DisplayName("should return all commands of client")
    void getCommandByClientId() {
        List<Command> all = repository.getCommandByClientId(2L);
        Assertions.assertEquals(all.size(),3);
        Command command1 = all.get(1);
        Assertions.assertEquals(2,command1.getId());
        Assertions.assertEquals("seif",command1.getClient().getFirstName());
        Assertions.assertEquals(1,command1.getDetails().size());
        Assertions.assertEquals("Pc",command1.getDetails().get(0).getProduct().getName());
    }

    @Test
    @Sql("/command.sql")
    @DisplayName("should return empty for client who has no commands")
    void getCommandByClientIdEmpty(){
        List<Command> all = repository.getCommandByClientId(4L);
        Assertions.assertEquals(0,all.size());
    }


    @Test
    @Sql("/command.sql")
    @DisplayName("should return a specific command for a specific client")
    void getCommandByIdAndClientId(){
        Optional<Command> commandOpt = repository.getCommandByIdAndClientId(2L,2L);

        Assertions.assertTrue(commandOpt.isPresent());
        Assertions.assertEquals(1,commandOpt.get().getDetails().size());
        Assertions.assertEquals(2,commandOpt.get().getClient().getId());
        Assertions.assertEquals(2,commandOpt.get().getId());
        Assertions.assertEquals(28,commandOpt.get().getCreatedAt().getDayOfMonth());
    }

    @Test
    @Sql("/command.sql")
    @DisplayName("should return empty optional if command doesn't exist")
    void getCommandByIdAndClientIdEmpty(){
        Optional<Command> commandOpt = repository.getCommandByIdAndClientId(2L,3L);

        Assertions.assertFalse(commandOpt.isPresent());

    }

}