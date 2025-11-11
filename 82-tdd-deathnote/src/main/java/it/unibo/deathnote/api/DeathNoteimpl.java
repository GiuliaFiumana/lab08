package it.unibo.deathnote.api;

import java.util.ArrayList;
import java.util.List;

public class DeathNoteimpl implements DeathNote{

    public static final Long TIME_TO_WRITE_CAUSE = 40L;
    public static final Long TIME_TO_WRITE_DETAILS = 6040L;

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

    @Override
    public boolean writeDetails(String details){
        if(personToKill.isEmpty()){
            throw new IllegalStateException("No name in the DeathNote");
        }
        long time = System.currentTimeMillis();
        this.personToKill.get(personToKill.size() - 1).details = details;
        long time2 = System.currentTimeMillis();
        if((time2 - time) > TIME_TO_WRITE_DETAILS){
            this.personToKill.get(personToKill.size() - 1).causeOfDeath = "";
        }
        return (time2 - time) < TIME_TO_WRITE_DETAILS;
    }

    @Override
    public String getDeathCause(String name){
        Person personToFind = findPerson(name);
        return personToFind.causeOfDeath;
        
    }

    @Override
    public String getDeathDetails(String name){
        Person personToFind = findPerson(name);
        return personToFind.details;
    }

    private  Person findPerson(String name) throws IllegalArgumentException{
        for(Person p : personToKill){
            if(p.name.equals(name)){
                return p;
            }
        }
        throw new IllegalArgumentException("the provider name is not written in this DeathNote");

    }
    
    @Override
    public boolean isNameWritten(String name){
        for(Person p : personToKill){
            if(p.name.equals(name)){
                return true;
            }
        }
        return false;
    }

}
