package thebardals.android.toyguay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import thebardals.android.toyguay.R;
import thebardals.android.toyguay.adapters.ToysAdapter;
import thebardals.android.toyguay.fragments.ToyListFragment;
import thebardals.android.toyguay.navigator.Navigator;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.model.Toys;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toys);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
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
        toy1.setPrice(99.00);
        Toy toy2 = new Toy();
        toy2.setName("toyPrueba 2");
        toy2.setImageURL("http://www.elpaisdelosjuguetes.es/media/catalog/product/cache/2/image/9df78eab33525d08d6e5fb8d27136e95/9/7/97251_1.jpg");
        toy1.setPrice(99.00);
        Toys toys = Toys.build();
        toys.add(toy1);
        toys.add(toy2);


        toyListFragment.setListener(new ToysAdapter.OnToyClick() {
            @Override
            public void clickedOn(Toy toy, int position) {
                goToToyDetail(toy);
            }
        });

        toyListFragment.setToys(toys);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toys, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void goToToyDetail(Toy toy) {
        Navigator.navigateFromToysActivityToToyDetailActivity(ToysActivity.this, toy);
    }
}
