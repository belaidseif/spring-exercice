package com.rampup.exerciseone.repository;

import com.rampup.exerciseone.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {

    List<Command> getCommandByClientId(Long clientId);

   Optional<Command>  getCommandByIdAndClientId(Long commandId, Long clientId);
}
