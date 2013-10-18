package me.znyupup.entity;

public class Board {
	
	private static final int SIZE = 4;

	private int[][] numbers;
	
	public int getSize(){
		return SIZE;
	}
	
	public int getNumber(int row, int col){
		return numbers[row][col];
	}
	
	public void setNumber(int row, int col, int value){
		numbers[row][col] = value;
	}
	
	public void setNumbers(int[][] numbers){
		this.numbers = numbers;
	}
	
	public int[][] getNumbers(){
		return numbers;
	}
	
	public Board(){
		numbers = new int[SIZE][SIZE];
	}
}
