package com.appstertech.tempmonitor.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appstertech.tempmonitor.BaseActivity;
import com.appstertech.tempmonitor.R;
import com.appstertech.tempmonitor.database.SharedPrefUtils;
import com.appstertech.tempmonitor.service.model.RefridgeGson;
import com.bumptech.glide.Glide;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {
    public static final String KEY_DATA_REFRIDGE_GSON = "DATA_REFRIDGE_GSON";
    @BindView(R.id.imageview_detail_header)
    ImageView mImgHeader;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.toolbar_detail)
    Toolbar mToolbar;
    @BindView(R.id.textview_content_detail)
    TextView mTvContent;
    @BindView(R.id.appbar_detail)
    AppBarLayout mAppbar;
    @BindView(R.id.button_detail_link_to_web)
    Button mBtnLinkToWeb;
    private AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (mCollapsingToolbar.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(mCollapsingToolbar)) {
                ViewCompat.animate(mBtnLinkToWeb).alpha(0).setDuration(400);
            } else {
                ViewCompat.animate(mBtnLinkToWeb).alpha(1).setDuration(400);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setData(getData());
    }

    private void linkToWeb(String url, Context context) {
        if (URLUtil.isNetworkUrl(url)) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            if (SharedPrefUtils.getUser(context) != null && !TextUtils.isEmpty(SharedPrefUtils.getUser(context).getUsername()) && !TextUtils.isEmpty(SharedPrefUtils.getUser(context).getPassword())) {
                String queryParameter = "?username=" + SharedPrefUtils.getUser(context).getUsername() + "&password=" + SharedPrefUtils.getUser(context).getPassword();
                i.setData(Uri.parse(url + queryParameter));
            } else {
                i.setData(Uri.parse(url));
            }


            context.startActivity(i);
        } else {
            Toast.makeText(context, "Link to web not available", Toast.LENGTH_SHORT).show();
        }
    }


    private void setData(RefridgeGson data) {
        if (null == data) {
            return;
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.finishAfterTransition(DetailActivity.this);
            }
        });
        mBtnLinkToWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkToWeb(getData().getUrlToWeb(), v.getContext());
            }
        });

        mCollapsingToolbar.setTitle(data.getRefrigeratorName());
        mCollapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(mCollapsingToolbar.getContext(), android.R.color.transparent));

        Glide.with(mImgHeader.getContext()).load(data.getImageString()).into(mImgHeader);

        mTvContent.setText("Name Contact : " + data.getContact() + " \n"
                + "HN NO : " + data.getRefrigeratorHNNo() + " \n"
                + "Band : " + data.getRefrigeratorBand() + " \n"
                + "Model : " + data.getRefrigeratorModel() + " \n"
                + "SN : " + data.getRefrigeratorSN() + " \n");

        mAppbar.addOnOffsetChangedListener(mListener);
    }

    private RefridgeGson getData() {
        if (getIntent() != null) {
            return getIntent().getParcelableExtra(KEY_DATA_REFRIDGE_GSON);
        } else {
            return new RefridgeGson();
        }
    }
}
