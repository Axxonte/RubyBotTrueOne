package fr.Axxonte.RubyBot;

import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;
import fr.Axxonte.RubyBot.command.commands.*;
import fr.Axxonte.RubyBot.command.commands.Moderation.WarnCommand;
import fr.Axxonte.RubyBot.command.commands.Music.*;
import fr.Axxonte.RubyBot.command.commands.Owner.DebugCommand;
import fr.Axxonte.RubyBot.command.commands.Owner.UpdateCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<>();
    public static CommandContext ctx;

    public CommandManager() {
        addCommand(new PingCommand());
        addCommand(new HelpCommand(this));
        addCommand(new PasteCommand());
        addCommand(new UpdateCommand());
        addCommand(new EdtCommand());
        addCommand(new MemeCommand());
        addCommand(new LyricsCommand());

        addCommand(new DebugCommand());

        addCommand(new JoinCommand());
        addCommand(new PlayCommand());
        addCommand(new LeaveCommand());
        addCommand(new SkipCommand());
        addCommand(new QueueCommand());
        addCommand(new NowPlayingCommand());
        addCommand(new VolumeCommand());
        addCommand(new RepeatCommand());
        addCommand(new PauseCommand());

    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already present"); 
        }

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    void handle(GuildMessageReceivedEvent event) throws IOException {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            ctx = new CommandContext(event, args);

            try {
                cmd.handle(ctx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
