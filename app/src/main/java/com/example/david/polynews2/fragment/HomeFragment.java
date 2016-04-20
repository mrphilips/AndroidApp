package com.example.david.polynews2.fragment;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.david.polynews2.ImageItem;
import com.example.david.polynews2.R;
import com.example.david.polynews2.storage.Copy;
import com.example.david.polynews2.adapter.GridViewAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by david on 19/04/2016.
 */
public class HomeFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static HomeFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        try{
            myMethod(view);
        }

        catch(IOException e){
            Log.e("ERROR : ", e.toString());
        }

        return view;
    }

    public void initializeWebView(View v) throws IOException{
        Copy.store(getContext(), "html/home.html", "databases/home.html");

        WebView webView = (WebView) v.findViewById(R.id.home_web);
        webView.loadUrl("file:///data/data/com.example.david.polynews2/databases/home.html");
    }
    public void myMethod(View v) throws IOException{

        initializeWebView(v);

        final ImageSwitcher sw;
        sw = (ImageSwitcher) v.findViewById(R.id.imageSwitcher);
        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getContext());
                myView.setScaleType(ImageView.ScaleType.CENTER);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            int i = 0;

            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 0)
                            sw.setImageResource(R.drawable.presentation1);
                        else if (i == 1) {
                            sw.setImageResource(R.drawable.presentation2);
                        } else if (i == 2) {
                            sw.setImageResource(R.drawable.presentation3);
                            i = -1;
                        }
                        i++;
                    }
                });

            }
        }, 0, 3000);


        TextView galerie = (TextView) v.findViewById(R.id.home_galerie);
        galerie.setText("Galerie Photos");
        GridView grid = (GridView)v.findViewById(R.id.gridView);
        ArrayList<ImageItem> test = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);


        for (int i = 0; i < 2; i++) { // remplacer 2 par imgs.size()
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            test.add(new ImageItem(bitmap, "Image#" + i));
        }

        GridViewAdapter gridAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout,test);
        grid.setAdapter(gridAdapter);



    }

}
