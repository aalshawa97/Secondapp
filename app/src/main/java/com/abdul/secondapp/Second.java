package com.abdul.secondapp;
  
import android.app.Activity;  
import android.content.Intent;  
import android.os.Bundle;  
import android.widget.TextView;  
  
/** 
 * Created by vivek on 8/26/13. 
 */  
public class Second extends Activity   
{  
 public void onCreate(Bundle savedInstanceState)   
 {  
  super.onCreate(savedInstanceState);  
  setContentView(R.layout.second);  
  
  TextView textview = (TextView) findViewById(R.id.textview);  
  Intent i = getIntent();  
  Bundle b = i.getExtras();  
  String text = b.getString("1");  
  textview.setText(text);  
  
 }  
} 