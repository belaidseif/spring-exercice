package com.rampup.exerciseone.controller;

import com.rampup.exerciseone.dto.CommandDto;
import com.rampup.exerciseone.service.CommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("rampup/api")
@Tag(name = "command api")
public class CommandController {


    private final CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }


    @Operation(description = "get all commands of specific client")
    @GetMapping("{clientId}/commands")
   public ResponseEntity<List<CommandDto>> getClientCommands(
           @PathVariable("clientId") @Valid Long clientId){
        List<CommandDto> clientCommands = commandService.getClientCommands(clientId);

        for (CommandDto commandDto:clientCommands){
            Link selfLink = linkTo(methodOn(CommandController.class).getCommandById(clientId,commandDto.getId()))
                    .withSelfRel();
            commandDto.add(selfLink);

        }


        return ResponseEntity.ok(clientCommands);
    }

    @GetMapping("{clientId}/commands/{commandId}")
   public ResponseEntity<CommandDto> getCommandById(
           @PathVariable("clientId") @Valid Long clientId,
           @PathVariable("commandId") @Valid Long commandId
    ){
        CommandDto commandDto = commandService.getCommandById(clientId, commandId);
        Link link = linkTo(methodOn(CommandController.class).getCommandById(clientId,commandDto.getId()))
                .withSelfRel();
            commandDto.add(link);
        return ResponseEntity.ok(commandDto);

    }
}
