package io.inspired.Publisher.models;

import java.io.Serializable;

public class Joke implements Serializable {
    private static final String JOKE_FORMAT = "Q: %s \nA: %s";

    private String setup;
    private String punchline;

    public Joke() {
    }

    public Joke(String setup, String punchline) {
        this.setup = setup;
        this.punchline = punchline;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    @Override
    public String toString() {
        return String.format(JOKE_FORMAT, this.setup, this.punchline);
    }
}
