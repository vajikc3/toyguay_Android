package thebardals.android.toyguay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import thebardals.android.toyguay.R;
import thebardals.android.toyguay.adapters.ToysAdapter;
import thebardals.android.toyguay.fragments.ToyListFragment;
import thebardals.android.toyguay.interactor.GetAllToysInteractor;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.model.Toys;
import thebardals.android.toyguay.navigator.Navigator;

public class ToysActivity extends AppCompatActivity {
    private ToyListFragment toyListFragment;
    private String filterQuery = "";
    private String filterCategory = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys);

        setToolbar();
        setActionButton();

        setToyListFragment();

    }

    private void setActionButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigator.navigateFromToysActivityToToySellActivity(ToysActivity.this);
            }
        });
    }
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toys_toolbar);
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
        new GetAllToysInteractor().execute(getApplicationContext(), new GetAllToysInteractor.GetAllToysInteractorResponse() {
            @Override
            public void response(Toys toys) {
                toyListFragment.setListener(new ToysAdapter.OnToyClick() {
                    @Override
                    public void clickedOn(Toy toy, int position) {
                        goToToyDetail(toy);
                    }
                });
                toyListFragment.setToys(toys);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toys, menu);
        MenuItem menuItem =  menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterQuery = query;
                toyListFragment.filter(filterQuery, filterCategory);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                filterQuery = query;
                toyListFragment.filter(filterQuery, filterCategory);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.activity_toys_action_filter:
                showFilterDialog();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void showFilterDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("Filtro")
                .customView(R.layout.dialog_filter_toys, true)
                .positiveText("Aplicar Filtro")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        RadioGroup selectCategory = (RadioGroup) dialog.getCustomView().findViewById(R.id.dialog_filter_category_selector);
                        RadioButton selectedCategory= (RadioButton) dialog.getCustomView().findViewById(selectCategory.getCheckedRadioButtonId());
                        filterCategory = selectedCategory.getTag().toString();
                        toyListFragment.filter(filterQuery, filterCategory);

                        RadioGroup selectDistance = (RadioGroup) dialog.getCustomView().findViewById(R.id.dialog_filter_distance_selector);
                        int selDistance = selectDistance.getCheckedRadioButtonId();
                    }
                })
                .build();
        RadioGroup selectCategory = (RadioGroup) dialog.getCustomView().findViewById(R.id.dialog_filter_category_selector);
        int count = selectCategory.getChildCount();
        for (int i=0;i<count;i++) {
            View o = selectCategory.getChildAt(i);
            if (o instanceof RadioButton) {
                if (o.getTag().toString().equals(filterCategory)){
                    ((RadioButton) o).setChecked(true);
                }
            }
        }
        dialog.show();
    }

    private void goToToyDetail(Toy toy) {
        Navigator.navigateFromToysActivityToToyDetailActivity(ToysActivity.this, toy);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            loadToys();
        }

    }
}
