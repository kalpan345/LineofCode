package com.example.demo;

public class fileData implements Comparable<fileData>{
    private int lineofcode;
    private String loc;

	public fileData(int linecount, String x) {
		this.lineofcode = linecount;
		this.loc = x;}

	public int getLineofcode() {
		return lineofcode;}
	public void setLineofcode(int lineofcode) {
		this.lineofcode = lineofcode;}

	public String getLoc() {
		return loc;}
	public void setLoc(String loc) {
		this.loc = loc;}  

	@Override
	public int compareTo(fileData o) {
		Integer i = new Integer(this.lineofcode);
		return i.compareTo(o.getLineofcode());
}}
