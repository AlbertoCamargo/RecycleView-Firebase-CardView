package code.listviewrecyclerandfirebase;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.index, null);


        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.itemsRecyclerView);

        this.mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        this.mRecyclerView.setLayoutManager(mLayoutManager);


        listView = (ListView) inflate.findViewById(R.id.listView);

       /* ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.item,
                R.id.tittle,
                values
        ); */
        context = getContext();
        fetchData();
        //CustomAdapter customAdapter = new CustomAdapter(getContext(), values);
        //listView.setAdapter(customAdapter);

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

                CustomAdapter customAdapter = new CustomAdapter(context, values);
                listView.setAdapter(customAdapter);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }



}
