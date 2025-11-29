package com.example.pdfreader;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.PageViewHolder> {
    private final MainActivity host;
    private final int pageCount;
    private final Point screenSize;

    public PageAdapter(MainActivity host, int pageCount, Point screenSize) {
        this.host = host;
        this.pageCount = pageCount;
        this.screenSize = screenSize;
    }

    @NonNull
    @Override
    public PageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView iv = new ImageView(parent.getContext());
        iv.setAdjustViewBounds(true);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);


        int orientation = parent.getResources().getConfiguration().orientation;
        if (orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
            iv.setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            ));
        } else {

            int halfW = screenSize.x / 2;
            iv.setLayoutParams(new RecyclerView.LayoutParams(halfW, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        return new PageViewHolder(iv);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewHolder holder, int position) {

        int w = holder.imageView.getLayoutParams().width;
        int h = holder.imageView.getLayoutParams().height;
        if (w <= 0) w = screenSize.x; // fallback
        if (h <= 0) h = screenSize.y;

        Bitmap bmp = host.renderPageToBitmap(position, w, h);
        holder.imageView.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return pageCount;
    }

    static class PageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public PageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView;
        }
    }
}
