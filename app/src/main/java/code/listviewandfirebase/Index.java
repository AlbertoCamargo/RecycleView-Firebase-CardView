package code.listviewandfirebase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Iterator;
import java.util.Vector;


/**
 * Created by Alberto Mario Camargo Castro on 11-Apr-16.
 */
public class Index extends Fragment {


    Vector<DataSnapshot> values =  new Vector<>();
    View inflate;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.index, null);

        listView = (ListView) inflate.findViewById(R.id.listView);

       /* ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.item,
                R.id.tittle,
                values
        ); */
        fetchData();
        //CustomAdapter customAdapter = new CustomAdapter(getContext(), values);
        //listView.setAdapter(customAdapter);

        return inflate;

    }

    public void fetchData() {
        Main.myFirebaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                values.clear();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    values.add(iterator.next());
                }

                CustomAdapter customAdapter = new CustomAdapter(getContext(), values);
                listView.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }



}
