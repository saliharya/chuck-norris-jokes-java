package com.advance.chucknorrisjokesapp.presentation.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.advance.chucknorrisjokesapp.domain.model.Joke;

import java.util.List;

public class JokeDiffCallback extends DiffUtil.Callback {

    private final List<Joke> oldList;
    private final List<Joke> newList;

    public JokeDiffCallback(@NonNull List<Joke> oldList, @NonNull List<Joke> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId()
                .equals(newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Joke oldJoke = oldList.get(oldItemPosition);
        Joke newJoke = newList.get(newItemPosition);
        return oldJoke.getValue().equals(newJoke.getValue()) &&
                oldJoke.getIconUrl().equals(newJoke.getIconUrl());
    }
}
