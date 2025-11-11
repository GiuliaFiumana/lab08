package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;
import java.util.ArrayList;
import java.util.List;

public class DeathNoteimpl implements DeathNote{

    public static final Long TIME_TO_WRITE_CAUSE = 40L;

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

    @Override
    public boolean writeDeathCause(String cause) throws IllegalStateException{
        if(cause == null || personToKill.isEmpty()){
            throw new java.lang.IllegalStateException("cause null or no name in the DethNote");
        }
        long time = System.currentTimeMillis();
        this.personToKill.get(personToKill.size() - 1).causeOfDeath = cause;
        long time2 =System.currentTimeMillis();
        if((time2 - time) > TIME_TO_WRITE_CAUSE){
            this.personToKill.get(personToKill.size() - 1).causeOfDeath = "Heart attack";
        }
        return (time2 - time) < TIME_TO_WRITE_CAUSE;
    }

    /**
     * After writing the cause of death, details of the death should be written in the next
     * 6 seconds and 40 milliseconds.
     *
     * @param details the details of the human's death
     * @return true if the details were written within 6 seconds and 40 milliseconds, false otherwise
     * @throws IllegalStateException if there is no name written in this DeathNote,
     *     or the details are null
     */
    
    @Override
    public boolean writeDetails(String details){

    }

}
