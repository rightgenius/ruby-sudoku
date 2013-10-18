package me.znyupup.entity;

public class Rules {
	/**
	 * 验证是否符合所有规则
	 * 
	 * @param b
	 * @return
	 */
	public static boolean assertAll(Board b){
		return assertRow(b) && assertCol(b) && assertSubGrid(b);
	}
	
	private static boolean assertRow(Board b){
		for(int row=0; row<b.getSize(); row++){//针对每一行
			CheckArray check = new CheckArray(b.getSize());
			for(int col=0; col<b.getSize(); col++){
				check.put(b.getNumber(row, col));
			}
			if(!check.checkAll()) return false; //有一行校验失败，说明失败
		}
		return true;
	}
	
	private static boolean assertCol(Board b){
		for(int col=0; col<b.getSize(); col++){
			CheckArray check = new CheckArray(b.getSize());
			
			for(int row=0; row<b.getSize(); row++){
				check.put(b.getNumber(row, col));
			}
			if(!check.checkAll()) return false;//有一列校验失败，说明失败
		}
		
		return true;
	}
	
	private static boolean assertSubGrid(Board b){
		int count = 4;
		for(int i=0; i<count; i++){
			int rowOffset = 0, colOffset = 0;
			switch(i){
			case 0:
				rowOffset = colOffset = 0;
				break;
			case 1:
				rowOffset = 2;
				colOffset = 0;
				break;
			case 2:
				rowOffset = 0;
				colOffset = 2;
				break;
			case 3:
				rowOffset = 2;
				colOffset = 2;
			}

			CheckArray check = new CheckArray(b.getSize());
			check.put(b.getNumber(rowOffset, colOffset));
			check.put(b.getNumber(rowOffset, colOffset+1));
			check.put(b.getNumber(rowOffset+1, colOffset));
			check.put(b.getNumber(rowOffset+1, colOffset+1));
			if(!check.checkAll()) return false;
		}
		return true;
	}
}

class CheckArray{
	boolean[] array;
	
	/**
	 * 新建一个校验数组。
	 * 假设要校验4个数中，一定要出现1，2，3，4这四个数字
	 * 当输入1时，array[0] = true
	 * 当输入2时，array[1] = true
	 * 当输入3时，array[2] = true
	 * 当输入4时，array[3] = true
	 * 
	 * 也就是说，4个数中出现1，2，3，4四个不同数字的充要条件是，array每个元素都为true
	 * 
	 * @param size
	 */
	CheckArray(int size){
		array = new boolean[size];
	}
	
	/**
	 * 填入一个数字
	 * 
	 * @param number
	 */
	void put(int number){
		if(number == 0) return;
		array[number-1] = true;
	}
	
	/**
	 * 检查是不是符合4个数都存在的条件
	 * 
	 * @return
	 */
	boolean checkAll(){
		for(boolean b: array){
			if(!b) return false;
		}
		return true;
	}
}
