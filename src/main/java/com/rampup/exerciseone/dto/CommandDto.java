package com.rampup.exerciseone.dto;



import com.rampup.exerciseone.model.Command;


import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommandDto{
    public CommandDto(Long id, ZonedDateTime createdAt, List<DetailDto> details) {
        this.id = id;
        this.createdAt = createdAt;
        this.details = details;
    }

    private Long id;

    private ZonedDateTime createdAt;


    private List<DetailDto> details = new ArrayList<>();

    public static CommandDto createFromEntity(Command command){

        List<DetailDto> details = command.getDetails().stream().map(e -> DetailDto.createFromEntity(e)).collect(Collectors.toList());
        return new CommandDto(
            command.getId(),
            command.getCreatedAt(), details
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<DetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<DetailDto> details) {
        this.details = details;
    }
}
