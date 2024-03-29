package com.rampup.exerciseone.service.impl;

import com.rampup.exerciseone.dto.CommandDto;
import com.rampup.exerciseone.exception.CommandException;
import com.rampup.exerciseone.model.Command;
import com.rampup.exerciseone.repository.CommandRepository;
import com.rampup.exerciseone.service.CommandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommandServiceImpl implements CommandService {

    private final CommandRepository commandRepository;

    public CommandServiceImpl(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @Override
    public List<CommandDto> getClientCommands(Long clientId) {

        List<Command> commands = commandRepository.getCommandByClientId(clientId);

        List<CommandDto> commandDtos = commands.stream().map(e -> CommandDto.createFromEntity(e)).collect(Collectors.toList());

        return commandDtos;
    }

    @Override
    public CommandDto getCommandById(Long clientId, Long commandId) {

        Optional<Command> commandOpt = commandRepository.getCommandByIdAndClientId( commandId,clientId);
        Command command = commandOpt.orElseThrow(() -> new CommandException.CommandNotFound("command not found"));
        return CommandDto.createFromEntity(command);
    }

}
