package fr.Axxonte.RubyBot.command.commands;

import fr.Axxonte.RubyBot.Config;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.Reddit;
import net.explodingbush.ksoftapi.enums.ImageType;

import java.io.IOException;
import java.util.List;

public class MemeCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {
        KSoftAPI api = new KSoftAPI(Config.get("KSOFT_TOKKEN"));
        EmbedBuilder embed = new EmbedBuilder();
        try {
            if (ctx.getArgs().isEmpty()) {
                Reddit reddit = api.getRedditImage(ImageType.RANDOM_MEME).execute();
                embed.setTitle("Random Meme")
                        .setColor(69)
                        .setFooter("Requested by " + ctx.getMessage().getAuthor().getName())
                        .addField("Title : ", reddit.getTitle(), true)
                        .addField("URL : ", reddit.getImageUrl(), true)
                        .addField("Subreddit : ", reddit.getSubreddit(), true)
                        .addField("Author : ", reddit.getAuthor(), true)
                        .setImage(reddit.getImageUrl());
            } else {
                Reddit reddit = api.getRedditImage(ImageType.RANDOM_REDDIT).setSubreddit(ctx.getArgs().get(0)).execute();
                embed.setTitle(reddit.getSubreddit())
                        .setColor(69)
                        .setFooter("Requested by " + ctx.getMessage().getAuthor().getName())
                        .addField("Title : ", reddit.getTitle(), true)
                        .addField("URL : ", reddit.getImageUrl(), true)
                        .addField("Author : ", reddit.getAuthor(), true)
                        .setImage(reddit.getImageUrl());
            }
        }catch (Exception e){
            ctx.getChannel().sendMessage(e.toString()).queue();
        }
        ctx.getChannel().sendMessage(embed.build()).queue();

    }

    @Override
    public String getName() {
        return "subreddit";
    }

    @Override
    public String getHelp() {
        return "Return random image from the subreddit provided (or random meme if used without args)";
    }

    @Override
    public List<String> getAliases() {
        return List.of("sr");
    }
}
