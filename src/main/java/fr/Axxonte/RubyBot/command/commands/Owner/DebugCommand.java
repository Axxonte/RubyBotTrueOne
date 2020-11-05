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

        /*for (int i = 0; i < 3; i++) {
            DebugObject test = new DebugObject(i, "test", (i*11));

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(test);

            FileWriter file = new FileWriter("test.txt", true);
            file.append(json);
            file.close();
        }*/
        ctx.getMessage().getMentionedMembers().get(0);
        ctx.getChannel().sendMessage(ctx.getMessage().getMentionedMembers().get(0).getUser().getAvatarUrl()).queue();
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
