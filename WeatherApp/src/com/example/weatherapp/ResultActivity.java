package com.example.weatherapp;

import com.example.weatherservice.GetProvince;
import com.example.weatherservice.GetWeatherbyCityName;
import com.example.weatherservice.getSupportCity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Xiang Yingfei
 * @date 2014.03.28
 *
 */
public class ResultActivity extends Activity{
	
	private TextView tvResult;
	private ProgressDialog proDialog;

	private int type = 1;
	private String pre = null;
	
	@Override
	public void onCreate(Bundle b){
		super.onCreate(b);
		this.setContentView(R.layout.activity_result);
		
		init();
		getPer();
		
		new initAsyncTask(type).execute(10);
	}
	
	private void init(){
		tvResult = (TextView)this.findViewById(R.id.tv_result);
	}
	
	private void getPer() throws NullPointerException{
		Intent intent = this.getIntent();
		type = intent.getIntExtra("type", 1);
		pre = intent.getStringExtra("pro");
	}
	
	private class initAsyncTask extends AsyncTask<Integer, Integer, String[]>{
		
		int mType = 1;
		public initAsyncTask(int mType){
			this.mType = mType; 
		}
		
		@Override
		protected void onPreExecute(){
			proDialog = createDialog("数据获取中...");
			proDialog.show();
		}

		@Override
		protected String[] doInBackground(Integer... arg0) {
			String result [] = null;
			int count = 0;
			switch(mType){
			case 1:GetProvince gp = new GetProvince();		
				do{
					result = gp.getPro();
				}while(result == null && count++<=3);
				break;
			case 2:
				getSupportCity gsp = new getSupportCity();		
				do{
					result = gsp.getCity(pre);
				}while(result == null && count++<=3);
				break;			
			case 3:
				GetWeatherbyCityName gwcn = new GetWeatherbyCityName();
				do{
					result = gwcn.getWeather(pre);
				}while(result == null && count++<=3);
				break;
				
			}		
			
			return result;
		}
		
		@Override
		protected void onPostExecute(String[] result){
			closeDialog();
			if(result == null){
				showToast("没有获取到数据!");
			}else{
				StringBuffer sb = new StringBuffer();
				sb.append("获取到的数据为...");
				for(int i=0;i<result.length;i++){
					sb.append(result[i]+"\n");
				}
				tvResult.setText(sb.toString());
			}
		}
		
	}
	
	private ProgressDialog createDialog(String message){
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage(message);
		return dialog;
	}
	
	private void closeDialog(){
		if(proDialog.isShowing() && proDialog!=null)
			proDialog.dismiss();
	}
	
	private void showToast(String text){
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
}