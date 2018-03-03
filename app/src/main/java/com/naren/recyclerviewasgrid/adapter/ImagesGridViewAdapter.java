package com.naren.recyclerviewasgrid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naren.recyclerviewasgrid.R;
import com.naren.recyclerviewasgrid.models.ImageMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by narendra on 20/01/18.
 */

public class ImagesGridViewAdapter extends RecyclerView.Adapter<ImagesGridViewAdapter.ViewHolder> implements Filterable {

    private Context mContext;
    private List<ImageMeta> mImageList;
    private List<ImageMeta> mImageListFiltered;
    private static  final String BASE_URL="https://farm5.staticflickr.com/";
    private static  final String SLASH="/";
    private static  final String UNDER_SCORE="_";
    private static  final String DOT_JPG=".jpg";

    public ImagesGridViewAdapter(Context context, List<ImageMeta> imageList) {
        this.mContext = context;
        this.mImageList = imageList;
        this.mImageListFiltered=imageList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup itemView = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_view, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvImageTitle.setText(mImageListFiltered.get(position).title);
        String imageUrl=BASE_URL+mImageListFiltered.get(position).server+SLASH+mImageListFiltered.get(position).id+UNDER_SCORE+mImageListFiltered.get(position).secret+DOT_JPG;
        Glide.with(mContext)
                .load(imageUrl)
                .crossFade()
                .placeholder(R.drawable.loading_icon)
                .error(R.drawable.loading_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .fitCenter()
                .into(holder.imgProgramBackground);

    }

    @Override
    public int getItemCount() {
        return mImageListFiltered.size();
    }

    @Override
    public Filter getFilter() {
         return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mImageListFiltered = mImageList;
                } else {
                    List<ImageMeta> filteredList = new ArrayList<>();
                    for (ImageMeta row : mImageList) {


                        // here we are looking for title
                        if (row.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mImageListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mImageListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mImageListFiltered = (ArrayList<ImageMeta>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgProgramBackground;
        private TextView tvImageTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            imgProgramBackground = itemView.findViewById(R.id.image_banner);
            tvImageTitle = itemView.findViewById(R.id.image_title);


        }
    }
}
