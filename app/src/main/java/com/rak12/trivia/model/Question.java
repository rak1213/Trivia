package com.rak12.trivia.model;

public class Question {
    private String q;
    private boolean ans;

    public Question(String q, boolean ans) {
        this.q = q;
        this.ans = ans;
    }

    public Question() {

    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public boolean isAns() {
        return ans;
    }

    public void setAns(boolean ans) {
        this.ans = ans;
    }

    @Override
    public String toString() {
        return "Question{" +
                "q='" + q + '\'' +
                ", ans=" + ans +
                '}';
    }
}



