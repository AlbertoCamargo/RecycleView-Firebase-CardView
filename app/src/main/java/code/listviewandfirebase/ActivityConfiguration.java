package code.listviewandfirebase;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

/**
 * Created by Alberto Mario Camargo Castro on 12-Apr-16.
 */
public class ActivityConfiguration extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        PreferencesFragment  configfrag = new PreferencesFragment();
        transaction.replace(android.R.id.content, configfrag);
        transaction.commit();

    }
}
