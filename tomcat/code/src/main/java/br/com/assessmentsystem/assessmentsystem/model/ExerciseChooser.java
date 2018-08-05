/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.model;

import java.util.ArrayList;

import br.com.assessmentsystem.assessmentsystem.dao.ExerciseChooserDao;

/**
 *
 * @author marcelo
 */
public class ExerciseChooser {
    ArrayList<Exercise> exercisesToDo = new ArrayList<Exercise>();
    ArrayList<Exercise> exercisesDone = new ArrayList<Exercise>();
    boolean canDoNextExercise;
    
    public Exercise chooseExercise(int user)
    {
        ExerciseChooserDao dao = new ExerciseChooserDao();
        
        Exercise exercise = dao.chooseExercise(user);
        
        //Exercise exercise = exercisesToDo.get((int)random);
        
        return exercise;
    } 
    
    private double average() {
        double totalMark=0,average;
        for(Exercise exercise : exercisesDone)
        {
            //totalMark += exercise.exerciseMark();
        }
        
        average = totalMark/exercisesDone.size();
        //javax.swing.JOptionPane.showMessageDialog(null, "Média : " + average);
        return average;
    }
    
    public boolean canDoNextExercise() {
         if(exercisesDone.size() >= 3)
            {
            if(exercisesDone.size() <= 7)
            {
                if(average() >= 8.0)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    } 
}
