package br.com.assessmentsystem.assessmentsystem.model;

public class DirectoryTree {
	
	String id;
	String parent;      // required
	String text;      // node text
	String icon;       // string for custom
	/*
		state       : {
		    opened    : boolean  // is the node open
		    disabled  : boolean  // is the node disabled
		    selected  : boolean  // is the node selected
		  }
	*/
}
