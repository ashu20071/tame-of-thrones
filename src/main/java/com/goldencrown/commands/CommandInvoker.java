package com.goldencrown.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldencrown.exceptions.NoSuchCommandException;

public class CommandInvoker {
    private final Map<String, ICommand> commandMap;

    public CommandInvoker() {
        this.commandMap = new HashMap<>();
    }

    public CommandInvoker(Map<String, ICommand> commandMap) {
        this.commandMap = commandMap;
    }

    public ICommand getCommand(String commandName) {
        return commandMap.get(commandName);
    } 

    public List<ICommand> getAllCommands() {
        return new ArrayList<>(commandMap.values());
    }

    public void registerCommand(String commandName, ICommand command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        ICommand command = getCommand(commandName);
        if (command == null)
            throw new NoSuchCommandException();
        command.execute(tokens);
    }
}
