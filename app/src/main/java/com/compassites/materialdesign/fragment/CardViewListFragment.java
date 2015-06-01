package com.compassites.materialdesign.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.compassites.materialdesign.MainActivity;
import com.compassites.materialdesign.R;
import com.compassites.materialdesign.adapter.CardListAdapter;
import com.compassites.materialdesign.model.Card;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannan.sa on 01/06/15.
 */
public class CardViewListFragment extends Fragment implements ObservableScrollViewCallbacks{

    List<Card> cards;
    CardListAdapter cardListAdapter;
    ProgressDialog pd;
    private RecyclerView.LayoutManager layoutManager;

    public static List<Card> getData(){

        List<Card> cardsList = new ArrayList<>();

        for(int i=0;i<10;i++){
            Card card = new Card();
            card.setName("Tester");
            card.setDesc("Testing Desc");
            cardsList.add(card);
        }

        return cardsList;

    }


    public CardViewListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_card_view, container, false);


        com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView mRecyclerView =
                (com.github.ksoichiro.android.observablescrollview.ObservableRecyclerView) rootView.findViewById(R.id.rv_card_list);


        cards = new ArrayList<>();
        cards = getData();

        cardListAdapter = new CardListAdapter(getActivity(), cards);
        layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(cardListAdapter);

        //3P Library
        mRecyclerView.setScrollViewCallbacks(this);
        //mRecyclerView.setOnScrollListener(onScrollListener);

        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onScrollChanged(int i, boolean b, boolean b1) {

    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        MainActivity activity = (MainActivity) getActivity();

        if (scrollState == ScrollState.UP) {
                activity.setToolbarVisibility(false, true);
        } else if (scrollState == ScrollState.DOWN) {
                activity.setToolbarVisibility(true, true);
        } else if (scrollState == ScrollState.STOP){
                activity.setToolbarVisibility(true,true);
        }
    }


    private View.OnTouchListener mScrollTouchListener = new View.OnTouchListener() {
        MainActivity activity = (MainActivity) getActivity();

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int THRESHOLD_TO_HIDE_OR_SHOW = 10;
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:
                    if (event.getHistorySize() > 0) {
                        float dy = event.getY() - event.getHistoricalY(0);
                        float ady = Math.abs(dy);

                        if (dy < 0 && ady > THRESHOLD_TO_HIDE_OR_SHOW) {
                            // Scrolling down...
                            activity.hideActionAndBottomBar();
                            break;
                        }
                        if (dy > 0 && ady > THRESHOLD_TO_HIDE_OR_SHOW) {
                            // Scrolling up...
                            activity.showActionAndBottomBar();
                            break;
                        }
                    }
                    break;
            }
            return false;
        }
    };


    private RecyclerView.OnScrollListener onScrollListener =  new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            MainActivity activity = (MainActivity) getActivity();

            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    activity.showActionAndBottomBar();
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    activity.hideActionAndBottomBar();
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:
                    activity.showActionAndBottomBar();
                    break;
            }
        }
    };



}
