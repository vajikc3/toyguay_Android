package thebardals.android.toyguay.navigator;


import android.content.Intent;

import thebardals.android.toyguay.activities.ToyDetailActivity;
import thebardals.android.toyguay.activities.ToysActivity;
import thebardals.android.toyguay.util.Constants;
import thebardals.android.toyguay.model.Toy;


public class Navigator {

    public static Intent navigateFromToysActivityToToyDetailActivity(final ToysActivity toysActivity, final Toy toy) {
        final Intent i = new Intent(toysActivity, ToyDetailActivity.class);
        i.putExtra(Constants.INTENT_KEY_TOY_DETAIL, toy);
        toysActivity.startActivity(i);
        return i;
    }

}