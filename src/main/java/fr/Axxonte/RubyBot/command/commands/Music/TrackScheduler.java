package fr.Axxonte.RubyBot.command.commands.Music;

import com.jagrosh.jdautilities.command.Command;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import fr.Axxonte.RubyBot.CommandManager;
import fr.Axxonte.RubyBot.command.CommandContext;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class schedules tracks for the audio player. It contains the queue of tracks.
 */
public class TrackScheduler extends AudioEventAdapter {
    private static AudioPlayer player = null;
    private final BlockingQueue<AudioTrack> queue;
    private Member selfM;

    /**
     * @param player The audio player this scheduler uses
     */
    public TrackScheduler(AudioPlayer player) {
        this.player = player;
        this.queue = new LinkedBlockingQueue<>();
        this.selfM = selfM;
    }

    /**
     * Add the next track to queue or play right away if nothing is in the queue.
     *
     * @param track The track to play or add to queue.
     */
    public void queue(AudioTrack track) {
        // Calling startTrack with the noInterrupt set to true will start the track only if nothing is currently playing. If
        // something is playing, it returns false and does nothing. In that case the player was already playing so this
        // track goes to the queue instead.
        if (!player.startTrack(track, true)) {
            queue.offer(track);
        }
    }

    public BlockingQueue<AudioTrack> getQueue() {
        return queue;
    }

    /**
     * Start the next track, stopping the current one if it is playing.
     */
    public void nextTrack() {
        // Start the next track, regardless of if something is already playing or not. In case queue was empty, we are
        // giving null to startTrack, which is a valid argument and will simply stop the player.
        player.startTrack(queue.poll(), false);

        //Nickname modifier

        AudioTrackInfo info = player.getPlayingTrack().getInfo();
        String newNick = "";

        try{
            wait(10000L);
        }catch (Exception e)
        {
            if (info.title.length() > 32){
                newNick = info.title.substring(0, 30);
            }
            else
            {
                newNick = info.title;
            }
            CommandManager.ctx.getSelfMember().modifyNickname("▶ " + newNick).queue();
        }
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        // Only start the next track if the end reason is suitable for it (FINISHED or LOAD_FAILED)
        if (endReason.mayStartNext) {
            nextTrack();

           /* //Nickname modifier

            AudioTrackInfo info = player.getPlayingTrack().getInfo();
            String newNick = "";

            try{
                wait(10000L);

            }catch (Exception e)
            {
                if (info.title.length() > 32){
                    newNick = info.title.substring(0, 30);
                }
                else
                {
                    newNick = info.title;
                }
                CommandManager.ctx.getSelfMember().modifyNickname("▶ " + newNick).queue();
            }*/
        }
    }

    public static AudioPlayer getAudioPlayer(){
        return player;
    }
}