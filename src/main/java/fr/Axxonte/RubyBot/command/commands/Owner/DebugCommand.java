package fr.Axxonte.RubyBot.command.commands.Owner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class DebugCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {
        if (ctx.getAuthor().getIdLong() != 223861762695364608L){
            return;
        }

        ctx.getChannel().sendMessage(ctx.getGuild().getSelfMember().getPermissions().toString()).queue();
    }

    @Override
    public String getName() {
        return "debug";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
