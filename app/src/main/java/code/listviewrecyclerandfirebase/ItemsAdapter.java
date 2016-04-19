package code.listviewrecyclerandfirebase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;


import java.util.Vector;

/**
 * Created by Alberto Mario Camargo Castro on 19-Apr-16.
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public Button delete;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.tittle);
            delete = (Button) v.findViewById(R.id.delete);

        }
    }

    private Vector<DataSnapshot> dataset;

    public ItemsAdapter(Vector<DataSnapshot> dataset) {
        this.dataset = dataset;
    }

    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ViewHolder holder, int position) {
        holder.title.setText( dataset.get(position).child("name").getValue().toString());


        holder.delete.setFocusableInTouchMode(false);
        holder.delete.setFocusable(false);
        holder.delete.setTag(dataset.get(position));




    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}
