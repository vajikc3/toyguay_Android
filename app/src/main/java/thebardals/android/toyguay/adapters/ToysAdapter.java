package thebardals.android.toyguay.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thebardals.android.toyguay.R;
import thebardals.android.toyguay.views.ToyCellViewHolder;
import thebardals.domain.Toy;
import thebardals.domain.Toys;

public class ToysAdapter extends RecyclerView.Adapter<ToyCellViewHolder>{

    private final LayoutInflater layoutInflater;
    private final Toys toys;

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
    public void onBindViewHolder(ToyCellViewHolder cell, int position) {
        final Toy toy = toys.get(position);
        cell.setModel(toy);
    }

    @Override
    public int getItemCount() {
        return (int) toys.size();
    }
}
