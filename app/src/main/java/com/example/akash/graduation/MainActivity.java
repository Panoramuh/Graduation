package com.example.akash.graduation;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DashFragment.OnFragmentInteractionListener,
        TicketFragment.OnFragmentInteractionListener,
        StatusFragment.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;
    private TextView name;
    private TextView email;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new DashFragment());
        fragmentTransaction.commit();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        name = (TextView) headerView.findViewById(R.id.navUserName);
        email = (TextView) headerView.findViewById(R.id.navUserEmail);
        name.setText(bundle.getString("userName"));
        email.setText(bundle.getString("userEmail"));
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        if(id == R.id.dashboard){
                            actionbar.setTitle("Dashboard");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.main_container, new DashFragment());
                            fragmentTransaction.commit();
                        }
                        else if(id == R.id.gradStatus){
                            actionbar.setTitle("Graduation Status");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.main_container, new StatusFragment());
                            fragmentTransaction.commit();

                        }
                        else if(id == R.id.orderTickets){
                            actionbar.setTitle("Order Tickets");
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.main_container, new TicketFragment());
                            fragmentTransaction.commit();
                        }
                        else if(id == R.id.orderClothes){
                            actionbar.setTitle("Order Clothes");
                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        //startActivity(i);
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }
}
