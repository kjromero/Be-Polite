package com.leonel.proyeccioncarga.leonel.ubicate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
/**
 * Created by Leonel on 24/04/2015.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Button btn = (Button)findViewById(R.id.imageView2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();


            }
        });







    }

    private void login() {

        Session.openActiveSession(this, true, new Session.StatusCallback() {



            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (session.isOpened()) {


                    Request.newMeRequest(session, new Request.GraphUserCallback() {


                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                                Log.i(null,user.getFirstName());
                                nombre= "  "+user.getFirstName()+" "+user.getLastName();
                                valLogin=true;
                                idface=user.getId();
                                Intent i = new Intent(MainActivity.this, findu.class);
Log.i(null,idface);
                                i.putExtra("nombreface", nombre);
                                i.putExtra("idface",idface);
                                startActivity(i);


                            }
                        }
                    }).executeAsync();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }



    static boolean valLogin=false;
    static String nombre;
    static String idface;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
