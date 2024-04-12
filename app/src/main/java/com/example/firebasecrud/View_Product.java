package com.example.firebasecrud;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebasecrud.databinding.FragmentViewProductBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;
public class View_Product extends Fragment
{
    RecyclerView recyclerView;
    ViewAdapter adapter;
    int pos;
    String uid;
    ImageView eimg;
    List<ProductData> list;
    SharedPreferences preferences;
    DatabaseReference mbase;
    private FragmentViewProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_view_product, container, false);
        recyclerView = view.findViewById(R.id.rcv_view_product);

        Log.d("MMM", "onCreateView: ");
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("Products")
                .limitToLast(50);


        mbase= FirebaseDatabase.getInstance().getReference();
        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<ProductData> options
                = new FirebaseRecyclerOptions.Builder<ProductData>()
                .setQuery(query, ProductData.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself

        adapter = new ViewAdapter(options);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}