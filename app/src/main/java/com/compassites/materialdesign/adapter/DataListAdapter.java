package com.compassites.materialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassites.materialdesign.model.Data;
import com.compassites.materialdesign.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Hannan.SA on 2/1/15.
 */
public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ListViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Data> info = Collections.emptyList();

    public DataListAdapter(Context context,List<Data> info){
        inflater = LayoutInflater.from(context);
        this.info = info;
        this.context = context;
    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Data information = info.get(position);
        holder.text.setText(information.text);
        holder.icon.setImageResource(information.iconId);

    }

    public void delete(int position){
        info.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView text;
        private ImageView icon;

        public ListViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            /*if(view.getId() == R.id.icon){
                Toast.makeText(context,"Clicked pos:"+getPosition(),Toast.LENGTH_LONG).show();
            }*/
            delete(getPosition());
        }


    }
}
