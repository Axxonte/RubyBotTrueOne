package fr.Axxonte.RubyBot.command.commands.Music;

import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class JoinCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        TextChannel channel = ctx.getChannel();
        AudioManager audioManager = ctx.getGuild().getAudioManager();

        if (audioManager.isConnected()) {
            channel.sendMessage("I'm already connected to a channel").queue();
            return;
        }

        GuildVoiceState memberVoiceState = ctx.getMember().getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("Please join a voice channel first").queue();
            return;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = ctx.getGuild().getSelfMember();

        if (!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)) {
            channel.sendMessageFormat("I am missing permission to join %s", voiceChannel).queue();
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        channel.sendMessage("Joining your voice channel").queue();
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getHelp() {
        return "Join your vocal channel.";
    }
}
