package com.rampup.exerciseone.controller;

import com.rampup.exerciseone.dto.CommandDto;
import com.rampup.exerciseone.model.Command;
import com.rampup.exerciseone.repository.CommandRepository;
import com.rampup.exerciseone.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("rampup/api")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @GetMapping("{clientId}/commands")
   public ResponseEntity<List<CommandDto>> getClientCommands(@PathVariable("clientId") @Valid Long clientId){

        return ResponseEntity.ok( commandService.getClientCommands(clientId));
    }

    @GetMapping("{clientId}/commands/{commandId}")
   public ResponseEntity<CommandDto> getCommandById(
           @PathVariable("clientId") @Valid Long clientId,
           @PathVariable("commandId") @Valid Long commandId
    ){
        CommandDto commandDto = commandService.getCommandById(clientId, commandId);
        return ResponseEntity.ok(commandDto);

    }
}
