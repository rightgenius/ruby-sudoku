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
	
	public Board(int[][] numbers){
		this.numbers = numbers;
	}
	
	public static Board generateBoardWithBlank(){
		Board b = new Board();
		int [][] temp = new int[4][4];
		int[] array0 = {1, 2, 3, 4};
		int[] array1 = {3, 4, 1, 2};
		int[] array2 = {2, 3, 4, 1};
		int[] array3 = {4, 1, 2, 3};
		
		//生成一组数组，随机对1、2行，或3、4行进行互换
		switch(((int)Math.random() * 100) % 4){
		case 0:
			temp[0] = array0;
			temp[1] = array1;
			temp[2] = array2;
			temp[3] = array3;
			break;
		case 1:
			temp[0] = array1;
			temp[1] = array0;
			temp[2] = array2;
			temp[3] = array3;
			break;
		case 2:
			temp[0] = array1;
			temp[1] = array0;
			temp[2] = array3;
			temp[3] = array2;
			break;
		case 3:
			temp[0] = array0;
			temp[1] = array1;
			temp[2] = array3;
			temp[3] = array2;
			break;
		}
		
		//随机的横纵轴互换
		int[][] array = new int[4][4];
		if(((int)(Math.random() * 100)) % 2 == 1){
			for(int i=0; i<4; i++){
				for(int j=0; j<4; j++){
					array[i][j] = temp[j][i];
				}
			}
		}else{
			array = temp;
		}
		
		
		//set random blank
		//int[][] checkArray = new int[4][4];
		for(int i=0;i<8;i++){
			int row = ((int)(Math.random()*100))%4;
			int col = ((int)(Math.random()*100))%4;
			if(array[row][col] == 0){
				i--;
			}else {
				array[row][col] = 0;
				}
		}
		
		b.setNumbers(array);
		return b;
	}
}
