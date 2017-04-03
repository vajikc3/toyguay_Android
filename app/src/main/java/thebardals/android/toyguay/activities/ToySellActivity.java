package thebardals.android.toyguay.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thebardals.android.toyguay.R;
import thebardals.android.toyguay.interactor.PutToyInteractor;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.util.Constants;

public class ToySellActivity extends AppCompatActivity {

    @BindView(R.id.toy_sell_button)
    Button _sellButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_sell);
        ButterKnife.bind(this);

        setToolbar();
        configureSellButton();

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toy_sell_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.sell);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void configureSellButton(){
        _sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Test Toy sell */
                Toy toy = new Toy();
                toy.setName("fakeName");
                toy.setDescription("fakeDescription");
                toy.setPrice(123.45);
                List<String> cat = new LinkedList<String>();
                cat.add("fakeCat1");
                cat.add("fakeCat2");
                toy.setCategories(cat);

                new PutToyInteractor().execute(getApplicationContext(), toy, new PutToyInteractor.PutToyInteractorResponse() {
                    @Override
                    public void response(int error) {
                        if (error == Constants.POST_TOY_OK) {
                            /* TODO Mensajito de todo bien */

                        }
                        if (error== Constants.POST_TOY_ERROR_AUTH){
                            /* TODO Saltar a actividad de LOGIN pq el token está caducado */
                        }
                        else{
                            /* TODO El error es otro, tema de parámetros */
                        }
                    }
                });
            }
        });
    }
}
