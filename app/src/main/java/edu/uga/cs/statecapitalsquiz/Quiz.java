package edu.uga.cs.statecapitalsquiz;

public class Quiz {

    private long   id;
    private String question;
    private String answer;
    private String xanswer1;
    private String xanswer2;

    public Quiz(){
        this.id = -1;
        this.question = null;
        this.answer = null;
        this.xanswer1 = null;
        this.xanswer2 = null;
    }

    public Quiz( String question, String answer, String xanswer1, String xanswer2){
        this.id = -1;
        this.question = question;
        this.answer = answer;
        this.xanswer1 = xanswer1;
        this.xanswer2 = xanswer2;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getXanswer1() {
        return xanswer1;
    }

    public void setXanswer1(String xanswer1) {
        this.xanswer1 = xanswer1;
    }

    public String getXanswer2() {
        return xanswer2;
    }

    public void setXanswer2(String xanswer2) {
        this.xanswer2 = xanswer2;
    }
}