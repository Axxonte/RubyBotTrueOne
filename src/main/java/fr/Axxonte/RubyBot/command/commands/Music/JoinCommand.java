package fr.Axxonte.RubyBot.command.commands.Music;

import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

public class JoinCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {

    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getHelp() {
        return "Join your vocal channel.";
    }
}
