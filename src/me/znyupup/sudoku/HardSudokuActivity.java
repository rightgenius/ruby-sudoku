package me.znyupup.sudoku;

import me.znyupup.entity.Board;
import me.znyupup.entity.Rules;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HardSudokuActivity extends Activity {
	
	private DisplayMetrics mDm;
	private TableLayout table;
	private TextView resultView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hard_sudoku);
		
		mDm = getResources().getDisplayMetrics();
		table = (TableLayout)findViewById(R.id.simple_table);
		resultView = (TextView)findViewById(R.id.result);
		doTableLayout();
		doValidateButton();
		doBackButton();
		doTableNumber();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hard_sudoku, menu);
		return true;
	}
	
	private void doTableLayout(){
		int screenWidth = mDm.widthPixels - (int)(20*mDm.density + 0.5f); //the table layout has left/right 10dip margin
		LayoutParams p = table.getLayoutParams();
		p.height = screenWidth;
		table.setLayoutParams(p);
	}
	
	private void doValidateButton(){
		Button b = (Button)findViewById(R.id.validate);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				int[][] sudoku = new int[4][4];
				
				for(int row=0; row<4; row++){
					TableRow tableRow = (TableRow)table.getChildAt(row);
					for(int col=0;col<4;col++){
						TextView tv = (TextView)tableRow.getChildAt(col);
						int value = Integer.parseInt("0"+tv.getText().toString());
						sudoku[row][col] = value;
					}
				}
				
				Board board = new Board();
				board.setNumbers(sudoku);
				if(Rules.assertAll(board)){
					resultView.setText("Congratulations! Your sudoku is right!");
				}else{
					resultView.setText("Oh-oh, your sudoku seems to have problems");
				}
			}
			
		});
	}
	
	private void doBackButton(){
		Button b = (Button)findViewById(R.id.return_home);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
		        intent.setClass(HardSudokuActivity.this, MainActivity.class);
	            startActivity(intent);
			}
				
		});
	}
	
	private void doTableNumber(){
		Log.d("HARD", "in table number");
		Board b = Board.generateBoardWithBlank();
		int[][] array = b.getNumbers();
		Log.d("HARD", "get the numbers array");
		for(int row=0; row<4; row++){
			TableRow tableRow = (TableRow)table.getChildAt(row);
			for(int col=0;col<4;col++){
				Log.d("HARD", Integer.valueOf(array[row][col]).toString());
				EditText et = (EditText)tableRow.getChildAt(col);
				if(array[row][col] > 0){
					et.setText(Integer.valueOf(array[row][col]).toString());
					et.setEnabled(false);
					et.setFocusable(false);
				}
			}
		}
	}
}
