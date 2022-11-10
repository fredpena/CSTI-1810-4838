package com.mdcgroup.myapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mdcgroup.myapplication.R;
import com.mdcgroup.myapplication.database.Word;

import java.util.List;
import java.util.function.Consumer;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordAdapterViewHolder> {

    private List<Word> mList;


    public WordAdapter() {
    }

    @NonNull
    @Override
    public WordAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);

        return new WordAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapterViewHolder holder, int position) {
        Word word = mList.get(position);
        holder.mWord.setText(word.getWord());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Word> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class WordAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView mWord;

        public WordAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mWord = itemView.findViewById(R.id.word);
        }
    }


}
