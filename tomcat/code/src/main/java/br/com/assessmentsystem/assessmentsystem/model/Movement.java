package br.com.assessmentsystem.assessmentsystem.model;

public class Movement {
	protected int id;

    public int getId() {
        return id;
    }
    
    protected int exerciseId;
    
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
        
    }

    public int getExerciseId() {
        return exerciseId;
    }

    protected int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    protected String codeUsed;
    
    public void setCodeUsed(String codeUsed) {
        this.codeUsed = codeUsed;
    }

    public String getCodeUsed() {
        return codeUsed;
    }

    
    protected double mark;
    
    public void setMark(double mark) {
          this.mark = mark;

    }

    public double getMark() {
    	return mark;
    }
}
