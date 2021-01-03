package fr.Axxonte.RubyBot;

import fr.Axxonte.RubyBot.command.ICommand;
import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        String prefix = "<@709136566056124566>";
        String prefix2 = "<@!709136566056124566>";
        System.out.println(event.getGuild().getSelfMember().getAsMention());
        String raw = event.getMessage().getContentRaw();

        if (user.isBot() || event.isWebhookMessage()) {
            return;
        }

       /* if (user.getIdLong() == 327690719085068289L && raw.startsWith(prefix)){
            return;
        }*/



        if (raw.equalsIgnoreCase(prefix + " shutdown")
                && user.getId().equals(Config.get("owner_id")) ||
                raw.equalsIgnoreCase(prefix2 + " shutdown")
                && user.getId().equals(Config.get("owner_id"))) {
            LOGGER.info("Shutting down");
            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());

            return;
        }

        if (raw.startsWith(prefix) || raw.startsWith(prefix2)) {
            try {
                manager.handle(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
