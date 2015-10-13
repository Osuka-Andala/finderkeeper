package com.osukaandalafinderkeeper.finderkeeper;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import io.fabric.sdk.android.Fabric;

import static com.osukaandalafinderkeeper.finderkeeper.R.id.fragment_navigation_drawer;

public class MainActivity extends AppCompatActivity {

    private static final String TWITTER_KEY = "aWTgFr1LMVkmQuyn7JLWy3iah";
    private static final String TWITTER_SECRET = "L4tU1EQElRYj0WITFAngsFWAT9GUm1nW7IaAD1RPzcUUgoyMdv";


    private Toolbar toolbar;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    String navTiles[];
    TypedArray navIcons;
    RecyclerView.Adapter recyclerViewAdapter;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits());
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        navTiles = getResources().getStringArray(R.array.navDrawerItems);
//        navIcons = getResources().obtainTypedArray(R.array.navDrawerIcons);
//
//        recyclerViewAdapter = new RecyclerViewAdapter(navTiles, navIcons, this);
//        recyclerView.setAdapter(recyclerViewAdapter);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
       drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout), toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(this,"Waddup, I am "+item.getTitle(),Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.navigate){
            startActivity(new Intent(this, SubActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
