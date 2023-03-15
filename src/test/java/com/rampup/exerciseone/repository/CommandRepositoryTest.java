package com.rampup.exerciseone.repository;

import com.rampup.exerciseone.model.Command;
import com.rampup.exerciseone.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CommandRepositoryTest {

    @Autowired private CommandRepository repository;



    @Test
    @Sql("/command.sql")
    @DisplayName("should return all commands of client")
    void getCommandByClientId() {
        List<Command> all = repository.getCommandByClientId(2L);
        Assertions.assertEquals(all.size(),3);
        Command command1 = all.get(1);
        Assertions.assertEquals(command1.getId(),2);
        Assertions.assertEquals(command1.getClient().getFirstName(),"seif");
        Assertions.assertEquals(command1.getDetails().size(),1);
        Assertions.assertEquals(command1.getDetails().get(0).getProduct().getName(),"Pc");
    }

    @Test
    @Sql("/command.sql")
    @DisplayName("should return empty for client who has no commands")
    void getCommandByClientIdEmpty(){
        List<Command> all = repository.getCommandByClientId(4L);
        Assertions.assertEquals(all.size(),0);
    }


    @Test
    @Sql("/command.sql")
    @DisplayName("should return a specific command for a specific client")
    void getCommandByIdAndClientId(){
        Optional<Command> commandOpt = repository.getCommandByIdAndClientId(2L,2L);

        Assertions.assertTrue(commandOpt.isPresent());
        Assertions.assertEquals(commandOpt.get().getDetails().size(),1);
        Assertions.assertEquals(commandOpt.get().getClient().getId(),2);
        Assertions.assertEquals(commandOpt.get().getId(),2);
        Assertions.assertEquals(commandOpt.get().getCreatedAt().getDayOfMonth(), 28);
    }

    @Test
    @Sql("/command.sql")
    @DisplayName("should return empty optional if command doesn't exist")
    void getCommandByIdAndClientIdEmpty(){
        Optional<Command> commandOpt = repository.getCommandByIdAndClientId(2L,3L);

        Assertions.assertFalse(commandOpt.isPresent());

    }

}