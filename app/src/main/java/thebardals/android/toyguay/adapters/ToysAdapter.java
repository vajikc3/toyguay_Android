package thebardals.android.toyguay.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thebardals.android.toyguay.R;
import thebardals.android.toyguay.views.ToyCellViewHolder;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.model.Toys;

public class ToysAdapter extends RecyclerView.Adapter<ToyCellViewHolder>{

    public interface OnToyClick {
        public abstract void clickedOn(Toy toy, int position);
    }
    private final LayoutInflater layoutInflater;
    private final Toys toys;
    private OnToyClick listener;

    public ToysAdapter(Toys toys, Context context){
        this.toys = toys;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ToyCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cell_toy, parent, false);
        return new ToyCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToyCellViewHolder cell, final int position) {
        final Toy toy = toys.get(position);
        cell.setModel(toy);
        cell.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (ToysAdapter.this.listener != null) {
                    ToysAdapter.this.listener.clickedOn(toy, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (int) toys.size();
    }

    public void setOnElementClickListener(@NonNull final OnToyClick listener) {
        this.listener = listener;
    }
}
