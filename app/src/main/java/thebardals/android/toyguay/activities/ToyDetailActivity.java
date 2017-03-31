package thebardals.android.toyguay.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import thebardals.android.toyguay.R;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.util.Constants;

public class ToyDetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_toy_detail_toy_name_text)
    TextView toyNameText;

    @BindView(R.id.activity_toy_detail_toy_description_text)
    TextView toyDescriptionText;

    @BindView(R.id.activity_toy_detail_toy_image)
    ImageView toyImage;

    @BindView(R.id.activity_toy_detail_toy_price)
    TextView toyPriceText;

    private Toy toy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_detail);
        setToolbar();

        ButterKnife.bind(this);

        getToyDetailFromCallingIntent();

        updateUI();


    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toy_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.toy_detail);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void getToyDetailFromCallingIntent() {
        Intent intent = getIntent();
        if(intent != null) {
            toy = (Toy) intent.getSerializableExtra(Constants.INTENT_KEY_TOY_DETAIL);
        }
    }

    private void updateUI() {
        toyPriceText.setText(String.valueOf(toy.getPrice()) + "€");
        toyNameText.setText(toy.getName());
        toyDescriptionText.setText(toy.getDescription());
        Picasso.with(this)
                .load(toy.getImageURL().get(0))
                .into(toyImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toy_list, menu);
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
}
