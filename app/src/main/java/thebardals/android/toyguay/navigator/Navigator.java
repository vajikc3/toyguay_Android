package thebardals.android.toyguay.navigator;


import android.content.Intent;

import thebardals.android.toyguay.activities.ToyDetailActivity;
import thebardals.android.toyguay.activities.ToySellActivity;
import thebardals.android.toyguay.activities.ToysActivity;
import thebardals.android.toyguay.model.Toy;
import thebardals.android.toyguay.util.Constants;


public class Navigator {

    public static Intent navigateFromToysActivityToToyDetailActivity(final ToysActivity toysActivity, final Toy toy) {
        final Intent i = new Intent(toysActivity, ToyDetailActivity.class);
        i.putExtra(Constants.INTENT_KEY_TOY_DETAIL, toy);
        toysActivity.startActivity(i);
        return i;
    }

    public static Intent navigateFromToysActivityToToySellActivity(final ToysActivity toysActivity) {
        final Intent i = new Intent(toysActivity, ToySellActivity.class);
        toysActivity.startActivityForResult(i,1);
        return i;
    }

}