package com.compassites.materialdesign.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.compassites.materialdesign.R;
import com.compassites.materialdesign.SubActivity;
import com.compassites.materialdesign.fragment.CardViewListFragment;
import com.compassites.materialdesign.model.Card;

import java.util.Collections;
import java.util.List;

/**
 * Created by hannan.sa on 01/06/15.
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ListViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    List<Card> cards = Collections.emptyList();

    public CardListAdapter(Context context,List<Card> disasters){
        inflater = LayoutInflater.from(context);
        this.cards = disasters;
        this.context = context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        Card card = cards.get(position);

        holder.cardName.setText(card.getName());
        holder.cardDesc.setText(card.getDesc());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cardBanner;
        TextView cardName;
        TextView cardDesc;

        public ListViewHolder(View itemView) {
            super(itemView);
            cardBanner = (ImageView) itemView.findViewById(R.id.banner);
            cardName = (TextView) itemView.findViewById(R.id.card_name);
            cardDesc = (TextView) itemView.findViewById(R.id.card_desc);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            context.startActivity(new Intent(context,SubActivity.class));

            /* MOVE TO NEW FRAGMENT
            DisasterDescriptionFragment disasterDescriptionFragment = new DisasterDescriptionFragment();

            FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();

            Bundle bundle = new Bundle();
            bundle.putSerializable("DISASTER",disasters.get(getPosition()));
            disasterDescriptionFragment.setArguments(bundle);

            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, disasterDescriptionFragment)
                    .addToBackStack(null)
                    .commit();
            */

        }


    }
}

