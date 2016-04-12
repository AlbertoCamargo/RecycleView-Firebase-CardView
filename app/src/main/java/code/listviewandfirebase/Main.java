package code.listviewandfirebase;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Config;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int CONFIG_REQUEST_CODE = 1;
    public static Firebase myFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Firebase
        Firebase.setAndroidContext(this);
        SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(this);
        String url = shp.getString("url","https://dazzling-inferno-7735.firebaseio.com/" );
        try {
            myFirebaseRef = new Firebase(url);
        } catch (Exception e) {
            myFirebaseRef = new Firebase("https://dazzling-inferno-7735.firebaseio.com/");
        }


        // Fragment initial
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, new Index()).commit();


        // fragment config

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = new Index();

        if (id == R.id.settings) {
            Intent i = new Intent(this, ActivityConfiguration.class);
            startActivityForResult(i, CONFIG_REQUEST_CODE);

            return true;

        } else if (id == R.id.create) {
            fragment = new Create();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();

        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONFIG_REQUEST_CODE) {
            SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(this);
            String url = shp.getString("url", "https://dazzling-inferno-7735.firebaseio.com/");
            Toast.makeText(this, "URL BD " + url, Toast.LENGTH_LONG).show();

            try {
                myFirebaseRef = new Firebase(url);
            } catch (Exception e) {
                myFirebaseRef = new Firebase("https://dazzling-inferno-7735.firebaseio.com/");
            }



        }
    }

    public void delete(View view) {
        DataSnapshot data = (DataSnapshot) view.getTag();
        data.getRef().removeValue();

    }

    public void create(View view) {
        EditText message = (EditText) findViewById(R.id.editText);

        Map<String, Object> item = new HashMap<>();

        item.put("name", message.getText());
        myFirebaseRef.push().setValue(item);

        message.setText("");

    }

    public void saveurl(View view) {
        EditText message = (EditText) findViewById(R.id.editText);

    }
}
