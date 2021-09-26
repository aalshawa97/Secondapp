package com.abdul.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DifferentScreenSizes extends Activity
{
    Button button1, button2;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portrait_landscape);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setText(R.string.about);
        button2.setText(R.string.help);

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent i = new Intent(DifferentScreenSizes.this, Second.class);
                Bundle b = new Bundle();
                String s = getResources().getString(R.string.help);
                b.putString("1", s);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}