package thebardals.android.toyguay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import thebardals.android.toyguay.R;
import thebardals.android.toyguay.fragments.ToyListFragment;
import thebardals.domain.Toy;
import thebardals.domain.Toys;

public class ToysActivity extends AppCompatActivity {
    private ToyListFragment toyListFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys);
        setToolbar();

        setToyListFragment();

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setToyListFragment() {
        toyListFragment = (ToyListFragment) this.getSupportFragmentManager().findFragmentById(R.id.activity_toys_frag_toy_list);
        loadToys();
    }

    private void loadToys(){
        Toy toy1 = new Toy();
        toy1.setName("toyPrueba 1");
        toy1.setImageURL("http://www.pediatricblog.es/wp-content/uploads/juguetes2.jpg");
        Toy toy2 = new Toy();
        toy2.setName("toyPrueba 2");
        toy2.setImageURL("http://www.elpaisdelosjuguetes.es/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/9/7/97251_1.jpg");
        Toys toys = Toys.build();
        toys.add(toy1);
        toys.add(toy2);

        toyListFragment.setToys(toys);


    }
}
