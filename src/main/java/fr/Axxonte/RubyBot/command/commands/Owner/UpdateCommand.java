package fr.Axxonte.RubyBot.command.commands.Owner;

import fr.Axxonte.RubyBot.Config;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdateCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException {
        if (!ctx.getAuthor().getId().equals(Config.get("Owner_id")))
            return;

        Process process = Runtime.getRuntime().exec("pwd");
        printResults(process);
        ctx.getChannel().sendMessage("Now Updating from the GitHub :heart:").queue();
        //System.exit(0);

    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getHelp() {
        return "Dev only";
    }
}
