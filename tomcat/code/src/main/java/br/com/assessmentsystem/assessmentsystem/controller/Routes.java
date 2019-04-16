/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.assessmentsystem.assessmentsystem.controller;

/**
 *
 * @author marcelo
 */
public class Routes {
   
    public static final String program = "", 
    						   login = program + "/login",
    						   logout = program + "/logout",
                               loginAct = login + "/act",
                               loginNew = login + "/new",
                               loginNewAct = loginNew + "/act",
                               loginShow = login + "/show",
                               main = program + "/main",
                               mainShow = main + "/show",
                               exercises = main + "/exercises",
                               exercisesNew = exercises + "/new",
                               exercisesAct = exercises + "/act", 
                               exercisesUpdate = exercises + "/update",
                               exercisesCreateFile = exercises + "/createFile",
                               exercisesCreateDirectory = exercises + "/createDirectory",
                               exercisesUpdateDirectoryTree = exercises+"/updateDirectoryTree",
                               exercisesOpenFile = exercises + "/openFile",
    						   exercisesSaveFile = exercises + "/saveFile";
}
