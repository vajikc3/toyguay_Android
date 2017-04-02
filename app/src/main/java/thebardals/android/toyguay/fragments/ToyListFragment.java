package thebardals.android.toyguay.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thebardals.android.toyguay.R;
import thebardals.android.toyguay.adapters.ToysAdapter;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.model.Toys;

public class ToyListFragment extends Fragment {

    private Toys toys;
    private Toys filteredToys;
    private RecyclerView toysRecyclerView;
    private ToysAdapter adapter;
    private ToysAdapter.OnToyClick listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_toy_list, container, false);
        toysRecyclerView = (RecyclerView) view.findViewById(R.id.toys_list_recycler_view);
        toysRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        return view;
    }

    public void setToys(Toys toys){
        this.toys = toys;
        this.filteredToys = toys;
        updateUI();
    }

    public void updateUI(){
        adapter = new ToysAdapter(filteredToys, getActivity());
        toysRecyclerView.setAdapter(adapter);

        adapter.setOnElementClickListener(new ToysAdapter.OnToyClick() {
            @Override
            public void clickedOn(Toy toy, int position) {
                if(ToyListFragment.this.listener != null){
                    ToyListFragment.this.listener.clickedOn(toy, position);
                }
            }
        });
    }

    public void filter(String query, String category) {
        if(query.isEmpty() && category.isEmpty()) {
            this.filteredToys= toys;
        } else {
            this.filteredToys = toys.filter(query, category);
        }

        updateUI();
    }


    public void setListener(ToysAdapter.OnToyClick listener) {
        this.listener = listener;
    }

}
