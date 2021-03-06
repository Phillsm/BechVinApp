package com.example.phill.bechvinapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.phill.bechvinapp.DataSource.ApiHelper;
import com.example.phill.bechvinapp.DataSource.DataSourceFacade;
import com.example.phill.bechvinapp.Model.Order;
import com.example.phill.bechvinapp.Model.Product;
import com.example.phill.bechvinapp.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class NewOrdersFragment extends Fragment implements AbsListView.OnItemClickListener, View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;
//    private HashMap<Product, Integer> productsForOrder = new HashMap<>();
    Order o = new Order(Order.Status.Created,MainActivity.costumerNumber,new Date(),"" );



        // TODO: Rename and change types of parameters
    public static NewOrdersFragment newInstance(String param1, String param2) {
        NewOrdersFragment fragment = new NewOrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewOrdersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }




        // TODO: Change Adapter to display your content

        ApiHelper api = new ApiHelper(getActivity().getBaseContext());

        ArrayList<Product> products = api.getAllWines();


        ArrayList<String> productstoStringed = new ArrayList();


        for(int i = 0; i < products.size(); i++)
        {

            productstoStringed.add(products.get(i).toString());

        }


        mAdapter = new ArrayAdapter<String>(getActivity(),

        android.R.layout.simple_list_item_1, android.R.id.text1, productstoStringed);


//        mAdapter = new ArrayAdapter<DummyContent.DummyItem>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, DummyContent.ITEMS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newproducts, container, false);

        // Set the adapter

        Button orderAddButton = (Button) view.findViewById(R.id.placeOrderButton);
        orderAddButton.setOnClickListener(this);

        mListView = (AbsListView) view.findViewById(R.id.listtest);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);



        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {



            ApiHelper api = new ApiHelper(getActivity().getBaseContext());
            ArrayList<Product> products = api.getAllWines();


            mListener.onFragmentInteraction(products.get(position).toString());


            Context context = getActivity().getApplicationContext();
            CharSequence text = "Added!  " + products.get(position).getName();
            int duration = Toast.LENGTH_SHORT;

            o.addProductAmount(products.get(position),1);
//          productsForOrder.put(products.get(position), 1);


            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    @Override
    public void onClick(View v) {

        //Order o = new Order(MainActivity.costumerNumber,productsForOrder,new Date(),"attperson");

        DataSourceFacade dsf = new DataSourceFacade(getActivity().getBaseContext());

        dsf.saveOrder(o);

        Context context = getActivity().getApplicationContext();
        CharSequence text = "Added!  " + o.toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }



}
