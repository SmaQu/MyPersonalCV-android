package com.example.smaqu.mypersonalcv.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.smaqu.mypersonalcv.adapter.CardViewAdapterMainActivity;
import com.example.smaqu.mypersonalcv.model.DatabaseHelper;
import com.example.smaqu.mypersonalcv.pojo.CardViewItem;
import com.example.smaqu.mypersonalcv.view.DetailActivity;
import com.example.smaqu.mypersonalcv.view.MainActivity;
import com.example.smaqu.mypersonalcv.view.MainActivityInterface;

import java.util.List;

/**
 * Created by SmaQu on 2017-12-22.
 */

public class MainPresenter implements MainPresenterInterface, CardViewAdapterMainActivity.OnClickCallback {

    private MainActivityInterface mainView;
    private List<CardViewItem> cardViewItemList;
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String BUNDLE_DESCRIPTION = "BUNDLE_DESC";

    public MainPresenter(MainActivityInterface mainView) {
    this.mainView = mainView;
    DatabaseHelper databaseHelper = new DatabaseHelper((Context) mainView);
    databaseHelper.createDatabase();
    cardViewItemList = databaseHelper.getCardViewList();
    }

    @Override
    public void createAdapter() {
        CardViewAdapterMainActivity adapter = new CardViewAdapterMainActivity((Context)mainView,cardViewItemList);
        mainView.createRecyclerView(adapter);
    }

    @Override
    public void openDialer(Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+"123456789"));
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(Context context, View view, int p) {
        Log.e("abc","Hello" );
        CardViewItem cardViewItem = cardViewItemList.get(p);
        Intent intent = new Intent(context, DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_DESCRIPTION, cardViewItem.getDescription());
        intent.putExtra(BUNDLE_EXTRAS,bundle);
        context.startActivity(intent);
    }
}
