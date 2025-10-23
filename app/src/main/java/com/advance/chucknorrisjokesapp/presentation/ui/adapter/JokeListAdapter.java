package com.advance.chucknorrisjokesapp.presentation.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.advance.chucknorrisjokesapp.R;
import com.advance.chucknorrisjokesapp.data.model.JokeResponse;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class JokeListAdapter extends RecyclerView.Adapter<JokeListAdapter.JokeViewHolder> {

    private final Context context;
    private final List<JokeResponse.JokeDto> jokeList = new ArrayList<>();

    public JokeListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<JokeResponse.JokeDto> jokes) {
        if (jokes == null) return;

        jokeList.clear();
        jokeList.addAll(jokes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public JokeListAdapter.JokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.joke_item, parent, false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeListAdapter.JokeViewHolder holder, int position) {
        JokeResponse.JokeDto joke = jokeList.get(position);

        holder.tvJoke.setText(joke.getValue());

        Glide.with(context)
                .load(joke.getIconUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.broken_image_placeholder)
                .into(holder.ivIcon);

        Log.d("JokeAdapter", "Joke value: " + joke.getValue());
    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }

    static class JokeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvJoke;

        public JokeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            tvJoke = itemView.findViewById(R.id.tvJoke);
        }
    }
}
