package com.goldencrown;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import com.goldencrown.commands.CommandInvoker;
import com.goldencrown.config.ApplicationConfig;

public class App {
    public static void main(String[] args) {

        // Initialize configuration and get registered commands
        ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();

        // Retrieve input text file path in args
        String filePath = args[0];
        File file = new File(filePath);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String inpuString;

            while ((inpuString = bufferedReader.readLine()) != null) {

                // Split input string by first whitespace as message can contain whitespace
                List<String> tokens = Arrays.asList(inpuString.split(" ", 2));

                // Execute command to send message to kingdoms
                commandInvoker.executeCommand("SEND_MESSAGE", tokens);
            }

            // Display output for SPACE kingdom if it conquered Southeros
            commandInvoker.executeCommand("DISPLAY_OUTPUT", Arrays.asList(new String[] { "SPACE" }));
            bufferedReader.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
