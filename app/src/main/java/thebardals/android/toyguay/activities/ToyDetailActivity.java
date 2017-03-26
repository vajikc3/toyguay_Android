package thebardals.android.toyguay.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import thebardals.android.toyguay.R;
import thebardals.android.toyguay.util.Constants;
import thebardals.android.toyguay.model.Toy;

public class ToyDetailActivity extends AppCompatActivity {
    @BindView(R.id.activity_toy_detail_toy_name_text)
    TextView toyNameText;

    @BindView(R.id.activity_toy_detail_toy_description_text)
    TextView toyDescriptionText;

    @BindView(R.id.activity_toy_detail_toy_image)
    ImageView toyImage;

    private Toy toy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toy_detail);

        ButterKnife.bind(this);

        getToyDetailFromCallingIntent();

        updateUI();
    }

    private void getToyDetailFromCallingIntent() {
        Intent intent = getIntent();
        if(intent != null) {
            toy = (Toy) intent.getSerializableExtra(Constants.INTENT_KEY_TOY_DETAIL);
        }
    }

    private void updateUI() {
        toyNameText.setText(toy.getName());
        toyDescriptionText.setText(toy.getDescription());
        Picasso.with(this)
                .load(toy.getImageURL())
                .into(toyImage);
    }
}
