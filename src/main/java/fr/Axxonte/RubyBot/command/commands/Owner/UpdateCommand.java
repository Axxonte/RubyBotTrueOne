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
        try {
            Runtime.getRuntime().exec("../../updateScript.sh");
            ctx.getChannel().sendMessage("Now Updating from the GitHub :heart:").queue();
            System.exit(0);
        }catch (IOException e){
            ctx.getChannel().sendMessage("Update script not found \uD83E\uDD28 ...").queue();
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
