package com.example.phill.bechvinapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phill.bechvinapp.DataSource.ApiHelper;
import com.example.phill.bechvinapp.DataSource.ApiMock;
import com.example.phill.bechvinapp.DataSource.DataSourceFacade;
import com.example.phill.bechvinapp.Model.Order;
import com.example.phill.bechvinapp.Model.Product;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends FragmentActivity implements
        Splash.OnFragmentInteractionListener,
        wineFragment.OnFragmentInteractionListener,
        NewProductsFragment.OnFragmentInteractionListener,
        OldOrdersFragment.OnFragmentInteractionListener
{

    ApiHelper api = new ApiHelper(getBaseContext());

    public static String costumerNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .addToBackStack(null)
                    .commit();
        }

        ///testtest
        ApiHelper apihelp = new ApiHelper(this);
        Order test = new Order(Order.Status.Created,"1337",new Date(),"attperson");
        test.addProductAmount(apihelp.getAllWines().get(2),2);
        test.addProductAmount(apihelp.getAllWines().get(12),5);

        DataSourceFacade dsf = new DataSourceFacade(this);

        dsf.saveOrder(test);
        //testest

    }


        public void clickedButton(View view){

            Splash newFragment = new Splash();


            EditText userId = (EditText) findViewById(R.id.userID);

            ArrayList<String> arr = api.getAllCustomers();

            //test
            DataSourceFacade dsf = new DataSourceFacade(this);
            int orderamount = dsf.getAllOrders().size();
            Toast.makeText(this,""+orderamount,Toast.LENGTH_LONG).show();
            //test
            if (arr.contains(userId.getText().toString())){
                costumerNumber = userId.getText().toString();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(Splash.class.getName());
                transaction.commit();
            }
            else {
                Toast.makeText(this,"Incorrect customer id",Toast.LENGTH_LONG).show();
            }


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
    public void onFragmentInteraction(Uri uri) {

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
            View view = inflater.inflate(R.layout.fragment_main, container, false);

            return view;
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0 ){
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

}
