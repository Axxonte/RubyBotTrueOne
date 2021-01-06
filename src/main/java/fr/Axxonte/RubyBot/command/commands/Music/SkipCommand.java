package fr.Axxonte.RubyBot.command.commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.concurrent.BlockingQueue;

public class SkipCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws InterruptedException {
        TextChannel channel = ctx.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(ctx.getGuild());
        TrackScheduler scheduler = musicManager.scheduler;
        AudioPlayer player = musicManager.player;
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();
        AudioTrack track = null;

        if (!ctx.getSelfMember().getVoiceState().getChannel().getMembers().contains(ctx.getMember())){
            ctx.getChannel().sendMessage("You need to be with me in a voice channel to change volume.").queue();
            return;
        }

        if (queue.size() == 0) {
            channel.sendMessage("There is nothing to play after.").queue();
            return;
        }
        track = scheduler.getQueue().peek();

        channel.sendMessage(new EmbedBuilder()
                .setTitle("\uD83C\uDFA7 Playing in " + ctx.getSelfMember().getVoiceState().getChannel().getName())
                .addField("", "<:skipBLANC:796354775221534771> Now playing " + track.getInfo().title + " by " + track.getInfo().author + "." , false)
                .setColor(new Color(235, 55, 200))
                .setThumbnail("https://img.youtube.com/vi/" + track.getInfo().identifier + "/0.jpg")
                .setFooter("Requested by " + ctx.getMessage().getAuthor().getName(), ctx.getAuthor().getAvatarUrl())
                .build()).queue();

        scheduler.nextTrack();
    }

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String getHelp() {
        return "Skips the music actually played in the player.";
    }
}
