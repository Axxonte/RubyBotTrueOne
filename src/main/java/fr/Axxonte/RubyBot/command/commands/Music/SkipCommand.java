package fr.Axxonte.RubyBot.command.commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.BlockingQueue;

public class SkipCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(ctx.getGuild());
        TrackScheduler scheduler = musicManager.scheduler;
        AudioPlayer player = musicManager.player;
        BlockingQueue<AudioTrack> queue = musicManager.scheduler.getQueue();

        if (queue.size() == 0) {
            channel.sendMessage("There is nothing to play after.").queue();

            return;
        }

        scheduler.nextTrack();

        channel.sendMessage("Skipping the current track").queue();

        //Nickname modifier

        try{
            wait(5000L);
        }catch (Exception e)
        {
            AudioTrackInfo info = player.getPlayingTrack().getInfo();
            ctx.getSelfMember().modifyNickname(info.title).queue();
        }
    }

    @Override
    public String getName() {
        return "skip";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
