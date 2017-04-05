package thebardals.android.toyguay.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.folderselector.FileChooserDialog;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import thebardals.android.toyguay.R;
import thebardals.android.toyguay.interactor.PutImageToyInteractor;
import thebardals.android.toyguay.interactor.PutToyInteractor;
import thebardals.android.toyguay.interactor.UploadImageToAzureInteractor;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.util.Constants;

public class ToySellActivity  extends AppCompatActivity implements FileChooserDialog.FileCallback {


    private Toy toy;
    private File file;
    @BindView(R.id.toy_sell_button)
    Button _sellButton;

    @BindView(R.id.activity_toy_sell_name)
    EditText _name;

    @BindView(R.id.activity_toy_sell_description)
    EditText _description;

    @BindView(R.id.activity_toy_sell_price)
    EditText _price;


    @BindView(R.id.activity_toy_sell_image1)
    ImageButton _image1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_sell);
        ButterKnife.bind(this);

        setToolbar();
        configureSellButton();
        configureImageButton();

        toy = new Toy();
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

                toy.setName(_name.getText().toString());
                toy.setDescription(_description.getText().toString());
                toy.setPrice(Double.parseDouble(_price.getText().toString()));
                List<String> cat = new LinkedList<String>();
                cat.add("default");
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

                    @Override
                    public void data(String id) {
                        Log.d("AOA", id);
                        toy.setId(id);

                        new PutImageToyInteractor().execute(getApplicationContext(), toy.getImageURL().get(0), id, new PutImageToyInteractor.PutImageToyInteractorResponse() {
                            @Override
                            public void PutImageDidFail(int error) {

                                setResult(RESULT_CANCELED);
                                finish();
                            }

                            @Override
                            public void PutImageSucess() {
                                setResult(RESULT_OK);
                                finish();
                            }
                        });

                    }
                });
            }
        });
    }
    private void configureImageButton(){
        _image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FileChooserDialog.Builder(ToySellActivity.this)
                        .initialPath("/sdcard/Download")
                        .mimeType("image/*")
                        .extensionsFilter(".png", ".jpg")
                        .tag("optional-identifier")
                        .goUpLabel("Up")
                        .show();
            }
        });


    }

    @Override
    public void onFileSelection(@NonNull FileChooserDialog dialog, @NonNull final File file) {

        new UploadImageToAzureInteractor().execute(file, new UploadImageToAzureInteractor.UploadImageToAzureResponse() {
            @Override
            public void sucess(String url) {
                toy.getImageURL().add(url);

                // After loading blot to Azure we can fill ImageButton with selected image
                Bitmap bmp = BitmapFactory.decodeFile(file.getPath());
                _image1.setImageBitmap(bmp);
            }

            @Override
            public void didFail() {
                /* TODO Error uloading image */
            }
        });
    }

    @Override
    public void onFileChooserDismissed(@NonNull FileChooserDialog dialog) {

    }
}
