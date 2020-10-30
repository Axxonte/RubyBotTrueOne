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

        if (!ctx.getSelfMember().getVoiceState().getChannel().getMembers().contains(ctx.getMember())){
            ctx.getChannel().sendMessage("You nee to be with me in a voice channel to change volume.").queue();
            return;
        }

        if (queue.size() == 0) {
            channel.sendMessage("There is nothing to play after.").queue();
            return;
        }

        scheduler.nextTrack();

        channel.sendMessage("Skipping the current track").queue();

        //Nickname modifier

        AudioTrackInfo info = player.getPlayingTrack().getInfo();
        String newNick = "";

        if (info.title.length() > 32){
            newNick = info.title.substring(0, 31);
        }
        else
        {
            newNick = info.title;
        }

        try{
            wait(10000L);
        }catch (Exception e)
        {
            ctx.getSelfMember().modifyNickname("▶" + newNick).queue();
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
