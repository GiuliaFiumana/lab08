package it.unibo.deathnote.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteimpl implements DeathNote{

    final List<Person> personToKill = new ArrayList<>();

    public Person createPerson(final String namePersonToKill){
        return this.new Person(namePersonToKill);
    }

    public class Person{

        private String name;
        private String causeOfDeath;
        private String details;

        public Person(final String name){
            this.name = name;
            this.causeOfDeath = "heart attack";
            this.details = "";
        }

    }
    
    @Override
    public String getRule(int ruleNumber) throws IllegalArgumentException{
        if(ruleNumber < 1 || ruleNumber > RULES.size()){
            final String msg = "Number given too large or too small";
            throw new java.lang.IllegalArgumentException(msg);
        }
        return RULES.get(ruleNumber - 1);
    }

    @Override
    public void writeName(String name) throws NullPointerException{
        if(name == null){
            throw new java.lang.NullPointerException("The name is null");
        }
        personToKill.add(createPerson(name));
    }


}
