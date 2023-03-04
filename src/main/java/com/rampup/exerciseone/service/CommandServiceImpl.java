package com.rampup.exerciseone.service;

import com.rampup.exerciseone.dto.CommandDto;
import com.rampup.exerciseone.exception.CommandException;
import com.rampup.exerciseone.model.Command;
import com.rampup.exerciseone.repository.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandServiceImpl implements CommandService{

    @Autowired private CommandRepository commandRepository;

    @Override
    public List<CommandDto> getClientCommands(String clientId) {
        if(!isLong(clientId) ){
            throw new CommandException.IdCannotBeParsed("client id is not a number");
        }
        List<Command> commands = commandRepository.getCommandByClientId(Long.parseLong(clientId));

        List<CommandDto> commandDtos = commands.stream().map(e -> CommandDto.createFromEntity(e)).collect(Collectors.toList());

        return commandDtos;
    }

    @Override
    public CommandDto getCommandById(String clientId, String commandId) {
        if(!isLong(clientId) || !isLong(commandId)){
            throw new CommandException.IdCannotBeParsed("one of the params is not a number");
        }
        Optional<Command> commandOpt = commandRepository.getCommandByIdAndClientId( Long.parseLong(commandId),Long.parseLong(clientId));
        Command command = commandOpt.orElseThrow(() -> new CommandException.CommandNotFound("command not found"));
        return CommandDto.createFromEntity(command);
    }

    private boolean isLong(String strNum) {
        if (strNum == null) {
            return false;
        }

        return strNum.chars().allMatch( Character::isDigit );
    }
}
