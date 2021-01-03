package fr.Axxonte.RubyBot.command.commands.Music;

import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

import java.io.IOException;

public class RepeatCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {
        PlayerManager playerManager = PlayerManager.getInstance();
        //playerManager
    }

    @Override
    public String getName() {
        return "repeat";
    }

    @Override
    public String getHelp() {
        return "Toggle repeat mode";
    }
}
