package com.example.phill.bechvinapp;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.phill.bechvinapp.DataSource.ApiHelper;
import com.example.phill.bechvinapp.DataSource.DataSourceFacade;
import com.example.phill.bechvinapp.DataSource.OrderDataSource;
import com.example.phill.bechvinapp.Model.Order;
import com.example.phill.bechvinapp.Model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;


public class MainActivity extends FragmentActivity implements wineFragment.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Date date = new Date();
        Product p1 = new Product("Testwine1","testid1",100.0);
        Product p2 = new Product("Testwine2","testid2",150.0);
        Order o1 = new Order(Order.Status.Created,"1011",date,"Phill");

        DataSourceFacade facade = new DataSourceFacade(this);

        facade.saveOrder(o1);

        List<Order> orders = facade.getAllOrders();

        Log.d("BechWineApp","getallOrders has return the numer of orders: " + orders.size());

        Log.d("BechWineApp","Order 1:"+orders.get(0));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


        public void clickedButton(View view){

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SplashScreenHolderFragment())
                    .commit();

        }

    public void browseWines(View view) {

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new BrowseWineFragmentPlaceHolder())
                .commit();

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(String id) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    public static class SplashScreenHolderFragment extends Fragment {

        public SplashScreenHolderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
            return rootView;
        }
    }


    public static class BrowseWineFragmentPlaceHolder extends Fragment {

        public BrowseWineFragmentPlaceHolder() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_wine_list, container, false);
            return rootView;
        }
    }



//        android.app.Fragment newFragment = wineFragment.newInstance("fds","fds");



}
