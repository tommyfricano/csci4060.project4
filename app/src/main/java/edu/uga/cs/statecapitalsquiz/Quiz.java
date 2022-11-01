package edu.uga.cs.statecapitalsquiz;

public class Quiz {

    private long   id;
    private String question;
    private String answer;

    public Quiz(){
        this.id = -1;
        this.question = null;
        this.answer = null;
    }

    public Quiz( String question, String answer){
        this.id = -1;
        this.question = "What is the capital of " + question;
        this.answer = answer;
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
}
