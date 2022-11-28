package com.mdcgroup.myapplication.ui;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.mdcgroup.myapplication.R;
import com.mdcgroup.myapplication.database.Product;
import com.mdcgroup.myapplication.utils.FirebaseNetwork;
import com.mdcgroup.myapplication.utils.NetResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordAdapterViewHolder> {

    private  List<Product> mList;
    private Consumer<Product> onClickListener;


    public WordAdapter() {
        this.mList = new ArrayList<>();
    }

    @NonNull
    @Override
    public WordAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);

        return new WordAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordAdapterViewHolder holder, int position) {
        Product product = mList.get(position);
        //holder.mWord.setText(word.getWord());

        holder.mName.setText(product.getName());
        holder.mBrand.setText(product.getBrand());
        holder.mPrice.setText(String.valueOf(product.getPrice()));

        holder.mName.setFocusable(false);
        holder.mBrand.setFocusable(false);
        holder.mPrice.setFocusable(false);

        holder.mBtnEdit.setOnClickListener(v -> {
            if (onClickListener != null) {
                onClickListener.accept(product);
            }
        });

        holder.mBtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "coming soon", Toast.LENGTH_LONG).show();
            }
        });

        FirebaseNetwork.obtain().download(String.format("images/%s.jpg", product.getUid()), new NetResponse<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.mImage.setImageBitmap(response);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(holder.itemView.getContext(), "Error download image", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Product> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(Consumer<Product> onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class WordAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImage;
        private TextInputEditText mName;
        private TextInputEditText mBrand;
        private TextInputEditText mPrice;
        private TextView mBtnCart;
        private Button mBtnEdit;

        public WordAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.name);
            mBrand = itemView.findViewById(R.id.brand);
            mPrice = itemView.findViewById(R.id.price);
            mBtnCart = itemView.findViewById(R.id.btnCart);
            mBtnEdit = itemView.findViewById(R.id.btnEdit);

        }
    }


}
