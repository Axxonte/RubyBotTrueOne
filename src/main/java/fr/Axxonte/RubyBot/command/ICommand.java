package fr.Axxonte.RubyBot.command;

import java.io.IOException;
import java.util.List;

public interface ICommand {
    void handle(CommandContext ctx) throws IOException, Exception;

    String getName();

    String getHelp();

    default List<String> getAliases() {
        return List.of(); // use Arrays.asList if you are on java 8
    }
}
