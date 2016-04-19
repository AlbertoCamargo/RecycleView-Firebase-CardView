package code.listviewrecyclerandfirebase;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Alberto Mario Camargo Castro on 11-Apr-16.
 */
public class PreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.configuration);


    }
}
