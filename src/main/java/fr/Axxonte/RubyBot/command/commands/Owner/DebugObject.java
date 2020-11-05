package fr.Axxonte.RubyBot.command.commands.Owner;

public class DebugObject {
    private int valueInt;
    private String valueString;
    private long idLong;

    public DebugObject(int valueInt, String valueString, long idLong){
        this.valueInt = valueInt;
        this.valueString = valueString;
        this.idLong = idLong;
    }

    public int getValueInt() {
        return valueInt;
    }

    public String getValueString() {
        return valueString;
    }

    public long getIdLong() {
        return idLong;
    }
}
