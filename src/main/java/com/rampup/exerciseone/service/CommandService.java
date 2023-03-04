package com.rampup.exerciseone.service;

import com.rampup.exerciseone.dto.CommandDto;

import java.util.List;

public interface CommandService {

   List< CommandDto> getClientCommands(String clientId );

   CommandDto getCommandById(String clientId, String commandId);
}
