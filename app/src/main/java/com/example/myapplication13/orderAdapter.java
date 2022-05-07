package com.example.myapplication13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.ViewHolder> {
    private Context context;
    private List<CheckoutList> cartItems;

    public orderAdapter(Context context, ArrayList<CheckoutList> cartItems){
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menuimage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CheckoutList item = cartItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivItem;
        private TextView tvItemName;
        private TextView tvDescription;
        private TextView tvPrice;
        private TextView tvRating;
        private RelativeLayout itemContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.ivimage);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            itemContainer = itemView.findViewById(R.id.itemContainer);
        }

        public void bind(CheckoutList item) {
            tvItemName.setText(item.getItem());
            tvDescription.setText("");
            tvPrice.setText("$" + item.getPrice());
            tvRating.setText("");
            Glide.with(context).load(item.getImageURL()).into(ivItem);
            itemContainer.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    System.out.println(getAdapterPosition());
                    if(item.getDelivery() == true) {
                        CompleteOrder completeOrder = new CompleteOrder();
                        completeOrder.setKEY_Price(item.getPrice());
                        completeOrder.setKeyAddress(item.getAddress());
                        completeOrder.setKeyQuantity(item.getQuantity());
                        completeOrder.setKeyItemname(item.getItem());
                        completeOrder.saveInBackground();
                    }
                    item.deleteInBackground();
                    cartItems.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    return true;
                }
            });
        }
    }
}
