package br.com.assessmentsystem.assessmentsystem.model;

import java.util.ArrayList;

public class Node {
	
	public String id;
	public String text;      // node text
	public String icon;       // string for custom
	/*
		state       : {
		    opened    : boolean  // is the node open
		    disabled  : boolean  // is the node disabled
		    selected  : boolean  // is the node selected
		  }
	*/
	public ArrayList<Node> children = null;
}
