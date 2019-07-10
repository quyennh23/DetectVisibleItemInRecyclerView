package com.quyennh.detectvisibleiteminrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "#MainActivity";

    private RecyclerView list;

    private adapter recyclerAdapter;

    private LinearLayoutManager layoutManager;

    private int visibleItemCount, totalItemCount, firstVisibleItemPosition, lastVisibleItem;
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
           //Log.d(TAG, "onScrolled() called with: recyclerView = [" + recyclerView + "], dx = [" + dx + "], dy = [" + dy + "]");
            visibleItemCount = layoutManager.getChildCount();
            totalItemCount = layoutManager.getItemCount();
            int lastVisible = layoutManager.findLastVisibleItemPosition();

            firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            //lastVisibleItem = firstVisibleItemPosition + visibleItemCount;
            Toast.makeText(MainActivity.this, "Visible Item Total:" + String.valueOf(visibleItemCount), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onScrolled: visibleItemCount = " + visibleItemCount
                    + "totalItemCount = " + totalItemCount
                    + "firstVisibleItemPosition = " + firstVisibleItemPosition
                    + "lastVisible = " + lastVisible
            );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.list);

        //Data
        ArrayList<String> country = new ArrayList<>();
        country.add("Nigeria");
        country.add("China");
        country.add("USA");
        country.add("Ghana");
        country.add("Canada");
        country.add("Finland");
        country.add("Denmark");
        country.add("Argentina");
        country.add("Andorra");
        country.add("Angola");
        country.add("Benin");
        country.add("Brazil");
        country.add("Chile");
        country.add("Denmark");
        country.add("Egypt");
        country.add("Fiji");
        country.add("France");
        country.add("Togo");

        layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        recyclerAdapter = new adapter(getApplicationContext(), country);
        list.addItemDecoration(new DividerItemDecoration(list.getContext(), layoutManager.getOrientation()));
        list.setAdapter(recyclerAdapter);
        list.addOnScrollListener(onScrollListener);
    }

    private class adapter extends RecyclerView.Adapter<adapter.myViewHolder> {
        Context context;
        List<String> mData;

        public adapter(Context context, List<String> data) {
            this.context = context;
            this.mData = data;
        }

        @Override
        public adapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter1, parent, false);
            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(adapter.myViewHolder holder, int position) {
            holder.country.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class myViewHolder extends RecyclerView.ViewHolder {
            TextView country;

            public myViewHolder(View itemView) {
                super(itemView);
                country = (TextView) itemView.findViewById(R.id.country);
            }
        }
    }

}