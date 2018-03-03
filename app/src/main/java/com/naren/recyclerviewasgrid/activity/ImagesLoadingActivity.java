package com.naren.recyclerviewasgrid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.naren.recyclerviewasgrid.R;
import com.naren.recyclerviewasgrid.adapter.ImagesGridViewAdapter;
import com.naren.recyclerviewasgrid.managers.ImageLoadingManager;
import com.naren.recyclerviewasgrid.models.ImageMeta;

import java.util.List;


/**
 * Created by narendra on 20/01/18.
 */

public class ImagesLoadingActivity extends AppCompatActivity implements ImageLoadingManager.ImagesListListener {

    private final static String TAG = "ImagesLoadingActivity";
    private RecyclerView mGridView;
    private ImagesGridViewAdapter mImagesGridViewAdapter;
    private GridLayoutManager mLayoutManager;
    private SearchView mSearchView;
    private RelativeLayout mLoadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_loading);
        //to getViews
        initViews();
        //to get the data from server
        ImageLoadingManager.singleton().getImageList(this);
    }

    private void listenSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mImagesGridViewAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mImagesGridViewAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private void initViews() {
        mGridView = findViewById(R.id.images_grid_view);
        mSearchView = findViewById(R.id.searchView);
        mLoadingLayout=findViewById(R.id.loading_layout);
        mLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<ImageMeta> imagesList) {
        mLoadingLayout.setVisibility(View.GONE);
        mGridView.setVisibility(View.VISIBLE);
        mLayoutManager = new GridLayoutManager(ImagesLoadingActivity.this, 2);
        mGridView.setHasFixedSize(true);
        mGridView.setLayoutManager(mLayoutManager);
        mImagesGridViewAdapter = new ImagesGridViewAdapter(ImagesLoadingActivity.this, imagesList);
        //Apply this adapter to the RecyclerView
        mGridView.setAdapter(mImagesGridViewAdapter);
        //listening to search query text change
        listenSearchView();
    }

    @Override
    public void onFailure(String errorMsg) {
        mLoadingLayout.setVisibility(View.GONE);
        Toast.makeText(ImagesLoadingActivity.this, errorMsg, Toast.LENGTH_LONG).show();
    }
}
