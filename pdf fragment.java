package com.example.pdfreader;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearSnapHelper;

public class PdfFragment extends Fragment {
    RecyclerView recyclerView;
    PageAdapter adapter;
    MainActivity host;

    public static PdfFragment newInstance() {
        return new PdfFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) host = (MainActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = new RecyclerView(requireContext());
        recyclerView.setId(View.generateViewId());
        setupRecyclerForOrientation(getResources().getConfiguration().orientation);
        return recyclerView;
    }

    private void setupRecyclerForOrientation(int orientation) {
        int pageCount = host.getPageCount();
        adapter = new PageAdapter(host, pageCount, getScreenSize());
        recyclerView.setAdapter(adapter);

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            LinearLayoutManager lm = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(lm);
            PagerSnapHelper snap = new PagerSnapHelper();
            snap.attachToRecyclerView(recyclerView);
        } else {

            LinearLayoutManager lm = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(lm);
            LinearSnapHelper snap = new LinearSnapHelper();
            snap.attachToRecyclerView(recyclerView);
        }
    }


    private Point getScreenSize() {
        WindowManager wm = (WindowManager) requireContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return new Point(metrics.widthPixels, metrics.heightPixels);
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setupRecyclerForOrientation(newConfig.orientation);
    }
}
