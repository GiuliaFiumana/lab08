package it.unibo.deathnote.api;

import java.util.ArrayList;
import java.util.List;

public class DeathNoteimpl implements DeathNote{

    @Override
    public String getRule(int ruleNumber) throws IllegalArgumentException{
        if(ruleNumber < 1 || ruleNumber > RULES.size()){
            final String msg = "Number given too large or too small";
            throw new java.lang.IllegalArgumentException(msg);
        }
        return RULES.get(ruleNumber - 1);
    }


}
