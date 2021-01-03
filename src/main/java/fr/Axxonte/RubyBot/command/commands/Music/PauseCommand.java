package fr.Axxonte.RubyBot.command.commands.Music;

import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

import javax.sound.midi.Track;
import java.io.IOException;

public class PauseCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {
        if(TrackScheduler.getAudioPlayer().isPaused()){
            ctx.getChannel().sendMessage("Le lecteur est déjà en pause.").queue();
            return;
        }

        TrackScheduler.getAudioPlayer().setPaused(true);
        ctx.getChannel().sendMessage("Et la musique disparût").queue();
    }

    @Override
    public String getName() {
        return "pause";
    }

    @Override
    public String getHelp() {
        return "Pauses the music player.";
    }
}
