package code.listviewrecyclerandfirebase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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


    static Vector<DataSnapshot> values =  new Vector<>();
    static View inflate;
    static ListView listView;
    static Context context;

    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.content_main, null);

        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.itemsRecyclerView);
        this.layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        this.recyclerView.setLayoutManager(layoutManager);



       // this.layoutManager = new LinearLayoutManager(inflater.getContext());
       // this.recyclerView.setLayoutManager(this.layoutManager);


        Main.myFirebaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                values.clear();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    values.add(iterator.next());
                }


                adapter = new ItemsAdapter(values);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        return inflate;

    }



    public static void fetchData() {

        Main.myFirebaseRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                values.clear();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    values.add(iterator.next());
                }


                adapter = new ItemsAdapter(values);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }




}
