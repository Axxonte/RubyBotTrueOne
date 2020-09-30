package fr.Axxonte.RubyBot.command.commands.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import net.dv8tion.jda.api.managers.AudioManager;

import java.io.IOException;

public class VolumeCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException {
        AudioPlayer audioPlayer = TrackScheduler.getAudioPlayer();
        if (ctx.getArgs().isEmpty()){
            ctx.getChannel().sendMessage("Actual player's volume : " + audioPlayer.getVolume() + ".").queue();
            return;
        }

        int oldVolume;
        int newVolume;

        oldVolume = audioPlayer.getVolume();
        newVolume = Integer.parseInt(ctx.getArgs().get(0));
        audioPlayer.setVolume(newVolume);
        ctx.getChannel().sendMessage("Player's volume : " + newVolume + ".").queue();
    }

    @Override
    public String getName() {
        return "volume";
    }

    @Override
    public String getHelp() {
        return "Change the music player's volume.";
    }
}
