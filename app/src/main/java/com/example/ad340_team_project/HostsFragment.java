package com.example.ad340_team_project;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HostsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return recyclerView;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView price;
        public TextView location;
        public TextView available;
        public Button select_btn;
        public Context context;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_hosts, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.host_name);
            price = (TextView) itemView.findViewById(R.id.host_price);
            location = (TextView) itemView.findViewById(R.id.host_location);
            available = (TextView) itemView.findViewById(R.id.host_available);
            select_btn = (Button) itemView.findViewById(R.id.like_btn);
            context = itemView.getContext();
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;
        private final String[] hNames;
        private final String[] hCosts;
        private final Drawable[] hPictures;
        private final String[] hAvailablities;
        private final String[] hLocations;


        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            hNames = resources.getStringArray(R.array.hosts_name);
            hCosts = resources.getStringArray(R.array.host_price);
            hLocations = resources.getStringArray(R.array.host_location);
            hAvailablities = resources.getStringArray(R.array.host_available);
            TypedArray a = resources.obtainTypedArray(R.array.host_picture);
            hPictures = new Drawable[a.length()];
            for (int i = 0; i < hPictures.length; i++) {
                hPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.picture.setImageDrawable(hPictures[position % hPictures.length]);
                holder.name.setText(hNames[position % hNames.length]);
                holder.price.setText(hCosts[position % hCosts.length]);
                holder.location.setText(hLocations[position % hLocations.length]);
                holder.available.setText(hAvailablities[position % hAvailablities.length]);

                holder.select_btn.setOnClickListener(view -> {
                    Intent intent = new Intent(getActivity(), HostActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString(Constants.KEY_NAME, hNames[position % hNames.length]);
                    bundle.putString(Constants.KEY_LOCATION, hLocations[position % hLocations.length]);
                    bundle.putString(Constants.KEY_PRICE, hCosts[position % hCosts.length]);
                    bundle.putString(Constants.KEY_AVAILABLE, hAvailablities[position % hAvailablities.length]);
                    bundle.putString(Constants.KEY_PICTURE, String.valueOf(hPictures[position % hPictures.length]));

                    intent.putExtras(bundle);
                    startActivity(intent);
                });
            }

            @Override
            public int getItemCount() {
                return LENGTH;
            }
        }
    }
