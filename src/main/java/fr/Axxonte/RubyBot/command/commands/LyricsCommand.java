package fr.Axxonte.RubyBot.command.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import fr.Axxonte.RubyBot.Config;
import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import fr.Axxonte.RubyBot.command.commands.Music.GuildMusicManager;
import fr.Axxonte.RubyBot.command.commands.Music.PlayerManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.Lyric;

import java.io.IOException;

public class LyricsCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws IOException, Exception {

        String args = "";
        GuildMessageReceivedEvent event = ctx.getEvent();
        TextChannel channel = event.getChannel();
        PlayerManager playerManager = PlayerManager.getInstance();
        GuildMusicManager musicManager = playerManager.getGuildMusicManager(event.getGuild(), ctx);
        AudioPlayer player = musicManager.player;

        for (String s : ctx.getArgs()) {
            args += " " + s;
        }

        KSoftAPI api = new KSoftAPI(Config.get("KSOFT_TOKKEN"));
        Lyric lyrics;

        if (ctx.getArgs().isEmpty())
        {
            if (player.getPlayingTrack() == null) {
                channel.sendMessage("The player is not playing any song.").queue();

                return;
            }

            AudioTrackInfo info = player.getPlayingTrack().getInfo();

            lyrics = api.getLyrics().search(info.title + " " + info.author).execute().get(0);
            String lyricString = lyrics.getLyrics();
            char c;
            for (int i = 0; i < lyricString.length(); i++)
            {
                c = lyricString.charAt(i);
                
            }
            ctx.getChannel().sendMessage(lyrics.getTitle() + " by " + lyrics.getArtistName() + '\n' + '\n' + lyrics.getLyrics()).queue();

        }else {

        lyrics = api.getLyrics().search(args).execute().get(0);
        /*EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(lyrics.getTitle() + " by " + lyrics.getArtistName())
                .setFooter("Requested by " + ctx.getMember().getNickname())
                .setColor(75)
                .setThumbnail(lyrics.getAlbumArtUrl());

        lyricString = lyrics.getLyrics();

        int i = 0;
        int j = 0;

        if (lyricString.length() >= 1024) {
            while (j < lyricString.length())
            {
                while (splited.length() < 1024) {
                    splited += lyricString.charAt(i);
                    i++;
                    j++;
                }
                i = 0;
                lyricString.replace(splited, "");
                embed.addField("", splited, false);
                splited = "";
            }
        }
        else
        {
            embed.addField("", lyricString, false);
        }


        ctx.getChannel().sendMessage(embed.build()).queue();*/
            ctx.getChannel().sendMessage(lyrics.getTitle() + " by " + lyrics.getArtistName() + '\n' + '\n' + lyrics.getLyrics()).queue();
        }
    }

    @Override
    public String getName() {
        return "lyrics";
    }

    @Override
    public String getHelp() {
        return null;
    }
}
