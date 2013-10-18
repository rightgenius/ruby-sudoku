package me.znyupup.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        doSpinnerSelectLevel();
    }
    
    /**
     * 关卡选择
     */
    private void doSpinnerSelectLevel(){
    	final Spinner s1 = (Spinner) findViewById(R.id.level_spinner);
        Log.i("MainActivity", s1.toString());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
    	
    	Button button = (Button)findViewById(R.id.button_level);
    	button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				int position = s1.getSelectedItemPosition();
				 Intent intent = new Intent();
				 if(position == 0){
		             intent.setClass(MainActivity.this, SimpleSudokuActivity.class);
				 }else{
					 intent.setClass(MainActivity.this, HardSudokuActivity.class);
				 }
	             startActivity(intent);
			}
    		
    	});
    }
    
    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
    private static final String[] spinnerStrings = {
    	"level 1",
    	"level 2"
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
