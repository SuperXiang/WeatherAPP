package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

/**
 * @author Xiang Yingfei
 * @date 2014.03.28
 *
 */
public class MainActivity extends Activity {

	private Button btn1,btn2,btn3;
	private EditText et1, et2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();       
        addListener();
    }
    
    private void init(){
    	btn1 = (Button)this.findViewById(R.id.btn_getprovince);
    	btn2 = (Button)this.findViewById(R.id.btn_getsupport);
    	btn3 = (Button)this.findViewById(R.id.btn_getweather);
    	
    	et1 = (EditText)this.findViewById(R.id.et_province);
    	et2 = (EditText)this.findViewById(R.id.et_city);
    }
    
    private void addListener(){
    	btn1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				action(1, null);
			}});
    	btn2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String pro = et1.getText().toString();
				if(pro.trim().equals("") || pro==null){
					showToast("输入不合法");
				}else{
					action(2, pro);
				}
			}});
    	btn3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String city = et2.getText().toString();
				if(city.trim().equals("") || city==null){
					showToast("输入不合法");
				}else{
					action(3, city);
				}
			}});  	
    }
    
    private void action(int type, String pro){
    	Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra("type", type);
		intent.putExtra("pro", pro);
		startActivity(intent);
    }
    
    private void showToast(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

}

