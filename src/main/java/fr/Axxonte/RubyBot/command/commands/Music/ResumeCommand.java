package fr.Axxonte.RubyBot.command.commands.Music;

import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

import java.io.IOException;

public class ResumeCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {
        if(!TrackScheduler.getAudioPlayer().isPaused()){
            ctx.getChannel().sendMessage("Le lecteur est déjà entrain de jouer.").queue();
            return;
        }

        TrackScheduler.getAudioPlayer().setPaused(false);
        ctx.getChannel().sendMessage("Et la musique fût.").queue();
    }

    @Override
    public String getName() {
        return "resume";
    }

    @Override
    public String getHelp() {
        return "Resume music player.";
    }
}
