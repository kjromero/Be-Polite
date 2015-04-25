package com.leonel.proyeccioncarga.leonel.ubicate;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by Leonel on 25/04/2015.
 */

public class findu extends Activity {
    String idface;
    private ImageView fotoface;
    private Bitmap loadedImage;
    private ViewFlipper viewFlipper;
    private float lastX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findu);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        TextView nombreface = (TextView) findViewById(R.id.textView2);

        //fotoface = (ImageView) findViewById(R.id.imageView4);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            String j = (String) b.get("nombreface");
            nombreface.setText(j);
            idface = (String) b.get("idface");

        }
        new DownloadImageTask((ImageView) findViewById(R.id.imageView4))
                .execute("https://graph.facebook.com/"+idface+"/picture?type=small");



        Button buscar =(Button) findViewById(R.id.button2);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i= new Intent(findu.this, MainActivity_dos.class);
                startActivity(i);
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_findu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();


                if (lastX < currentX)
                {

                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show the next Screen
                    viewFlipper.showNext();
                }

                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();
                }
                break;
            }
        }
        return false;
    }
}
