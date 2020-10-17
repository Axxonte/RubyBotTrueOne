package fr.Axxonte.RubyBot.command.commands;

import fr.Axxonte.RubyBot.Config;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.Reddit;
import net.explodingbush.ksoftapi.enums.ImageType;

import java.io.IOException;

public class MemeCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {
        KSoftAPI api = new KSoftAPI(Config.get("KSOFT_TOKKEN"));
        if(ctx.getArgs().isEmpty())
        {
            Reddit reddit = api.getRedditImage(ImageType.RANDOM_MEME).execute();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("Random Meme")
                    .setColor(69)
                    .setFooter("Requested by " + ctx.getMessage().getAuthor().getName())
                    .addField("Title : ", reddit.getTitle(), true)
                    .addField("URL : ", reddit.getImageUrl(), true)
                    .addField("Subreddit : ", reddit.getSubreddit(), true)
                    .addField("Author : ", reddit.getAuthor(), true)
                    .setImage(reddit.getImageUrl());
            ctx.getChannel().sendMessage(embed.build()).queue();
        }
        else
        {
            Reddit reddit = api.getRedditImage(ImageType.RANDOM_REDDIT).setSubreddit(ctx.getArgs().get(0)).execute();
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle(reddit.getSubreddit())
                    .setColor(69)
                    .setFooter("Requested by " + ctx.getMessage().getAuthor().getName())
                    .addField("Title : ", reddit.getTitle(), true)
                    .addField("URL : ", reddit.getImageUrl(), true)
                    .addField("Subreddit : ", reddit.getSubreddit(), true)
                    .addField("Author : ", reddit.getAuthor(), true)
                    .setImage(reddit.getImageUrl());
            ctx.getChannel().sendMessage(embed.build()).queue();
        }

    }

    @Override
    public String getName() {
        return "meme";
    }

    @Override
    public String getHelp() {
        return "Return random image from the subreddit provided (or random subreddit if used without args)";
    }
}
