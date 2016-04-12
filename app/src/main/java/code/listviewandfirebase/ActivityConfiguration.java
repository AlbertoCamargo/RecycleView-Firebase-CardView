package code.listviewandfirebase;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alberto Mario Camargo Castro on 12-Apr-16.
 */
public class ActivityConfiguration extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", 1);
        setResult(Activity.RESULT_OK, returnIntent);


        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction  = manager.beginTransaction();
        PreferencesFragment  configfrag = new PreferencesFragment();
        transaction.replace(android.R.id.content, configfrag);
        transaction.commit();



        //finish();
    }

    public void create(View view) {
        EditText message = (EditText) findViewById(R.id.editText);

        Map<String, Object> item = new HashMap<>();

        item.put("name", message.getText());
        Main.myFirebaseRef.push().setValue(item);

        message.setText("");

    }
}
