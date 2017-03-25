package thebardals.android.toyguay.views;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import thebardals.android.toyguay.R;
import thebardals.domain.Toy;

public class ToyCellViewHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private TextView priceTextView;
    private ImageView imageView;

    private WeakReference<Context> context;

    public ToyCellViewHolder(View cellToy){
        super(cellToy);
        context = new WeakReference<Context>(cellToy.getContext());

        bindViews(cellToy);
    }

    public void bindViews(View cellToy){
        nameTextView = (TextView) cellToy.findViewById(R.id.cell_toy_name);
        priceTextView = (TextView) cellToy.findViewById(R.id.cell_toy_price);
        imageView = (ImageView) cellToy.findViewById(R.id.cell_toy_image);
    }

    public void setModel(@NonNull Toy toy) {
        if (toy == null){
            return;
        }
        nameTextView.setText(toy.getName());
        priceTextView.setText(String.valueOf(toy.getPrice()));
        Picasso.with(context.get())
                .load(toy.getImageURL())
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .placeholder(android.R.drawable.ic_dialog_email)
                .into(imageView);
    }
}
