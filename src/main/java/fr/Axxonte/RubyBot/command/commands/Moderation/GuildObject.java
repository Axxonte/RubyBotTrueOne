package fr.Axxonte.RubyBot.command.commands.Moderation;

import net.dv8tion.jda.api.entities.Member;

import java.util.ArrayList;
import java.util.HashMap;

public class GuildObject {
    private long id;
    private ArrayList<HashMap<Member, Integer>> listMembers;

    public GuildObject(long id){
        this.id = id;
    }

    public GuildObject(long id, ArrayList<HashMap<Member, Integer>> listMembers) {
        this.id = id;
        this.listMembers = listMembers;
    }

    public void setListMembers(ArrayList<HashMap<Member, Integer>> listMembers) {
        this.listMembers = listMembers;
    }

    public long getId() {
        return id;
    }

    public ArrayList<HashMap<Member, Integer>> getListMembers() {
        return listMembers;
    }
}
