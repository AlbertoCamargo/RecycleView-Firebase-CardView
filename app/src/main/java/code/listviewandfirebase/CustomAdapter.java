package code.listviewandfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;

import java.util.Vector;

/**
 * Created by Alberto Mario Camargo Castro on 12-Apr-16.
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private Vector<DataSnapshot> values;

    public CustomAdapter(Context context, Vector<DataSnapshot> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(R.layout.item, null);
        }

        String value = values.get(position).child("name").getValue().toString();

        TextView title = (TextView) convertView.findViewById(R.id.tittle);
        Button delete = (Button) convertView.findViewById(R.id.delete);
        delete.setFocusableInTouchMode(false);
        delete.setFocusable(false);
        delete.setTag(values.get(position));


        title.setText(value);
        convertView.setTag(value);
        return convertView;
    }
}
