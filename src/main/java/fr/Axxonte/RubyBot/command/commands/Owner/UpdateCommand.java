package fr.Axxonte.RubyBot.command.commands.Owner;

import fr.Axxonte.RubyBot.Config;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

import java.io.IOException;

public class UpdateCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException {
        if (!ctx.getAuthor().getId().equals(Config.get("Owner_id")))
            return;

        Runtime.getRuntime().exec("pwd");
        ctx.getChannel().sendMessage("Now Updating from the GitHub :heart:").queue();
        System.exit(0);

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
