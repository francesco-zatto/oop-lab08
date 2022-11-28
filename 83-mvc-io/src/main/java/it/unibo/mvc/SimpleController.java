package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> history = new LinkedList<>();
    private String nextString = null;

    @Override
    public void print() {
        try {
            Objects.requireNonNull(this.nextString);
        } catch (NullPointerException npe) {
            throw new IllegalStateException("String not defined!");
        }
        System.out.println(this.nextString);
        this.history.add(this.nextString);
    }

    public List<String> getHistory() {
        return Collections.unmodifiableList(this.history);
    } 

    public void setNextString(final String s) {
        this.nextString = s;
    }

    public String getNextString() {
        return this.nextString;
    }



}
