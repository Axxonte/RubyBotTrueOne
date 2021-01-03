package fr.Axxonte.RubyBot.command.commands.Moderation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class WarnCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {
        //Conditions utilisation commande
        if (!(ctx.getMember().getPermissions().contains(Permission.ADMINISTRATOR) || ctx.getMember().getPermissions().contains(Permission.BAN_MEMBERS) || ctx.getMember().getPermissions().contains(Permission.KICK_MEMBERS))){
            ctx.getChannel().sendMessage("You can't use this command.").queue();
            return;
        }

        if (ctx.getMessage().getMentionedMembers().size() != 1){
            ctx.getChannel().sendMessage("Too much or not enough mention.").queue();
            return;
        }

        if (ctx.getArgs().isEmpty()){
            ctx.getChannel().sendMessage("Please provide some arguments.").queue();
            return;
        }

        Gson gson = new Gson();

        //Variables
        long idWarned = ctx.getMessage().getMentionedMembers().get(0).getIdLong();
        GuildObject server = new GuildObject(ctx.getGuild().getIdLong());
        File file = new File("test.txt");

        //Code

        //gson.toJson(idWarned, file);

    }

    @Override
    public String getName() {
        return "warn";
    }

    @Override
    public String getHelp() {
        return "Add a warn to a user";
    }
}
