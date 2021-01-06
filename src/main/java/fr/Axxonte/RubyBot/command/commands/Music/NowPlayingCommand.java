package fr.Axxonte.RubyBot.command.commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class NowPlayingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        GuildMessageReceivedEvent event = ctx.getEvent();
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild());
        AudioPlayer player = musicManager.player;
        AudioTrack track = null;
        TrackScheduler scheduler = musicManager.scheduler;

        track = musicManager.player.getPlayingTrack();


        if (player.getPlayingTrack() == null) {
            channel.sendMessage("The player is not playing any song.").queue();

            return;
        }

        if(track.getInfo().isStream){
            EmbedBuilder embed = new EmbedBuilder();
            embed.setTitle("<:djBlanc:796414818629845032> Actually playing in " + ctx.getSelfMember().getVoiceState().getChannel().getName())
                    .addField("", "Playing " + track.getInfo().title + "by " + track.getInfo().author + "." , false)
                    .addField("", ":red_circle: : Streaming" , false)
                    .setFooter("Request by " + ctx.getAuthor().getName(), ctx.getAuthor().getAvatarUrl())
                    .setColor(new Color(235, 52, 198));
            return;
        }

        AudioTrackInfo info = player.getPlayingTrack().getInfo();

        channel.sendMessage(EmbedUtils.embedMessage(String.format(
                "**Playing** [%s](%s)\n%s %s - %s",
                info.title,
                info.uri,
                player.isPaused() ? "\u23F8" : "▶",
                formatTime(player.getPlayingTrack().getPosition()),
                formatTime(player.getPlayingTrack().getDuration())
        )).setColor(new Color(235, 52, 198)).build()).queue();
    }

    @Override
    public String getName() {
        return "np";
    }

    @Override
    public String getHelp() {
        return "Display the sound actually played in the channel.";
    }

    private String formatTime(long timeInMillis) {
        final long hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final long minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final long seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
