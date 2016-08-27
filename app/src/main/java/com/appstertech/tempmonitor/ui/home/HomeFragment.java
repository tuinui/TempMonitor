package com.appstertech.tempmonitor.ui.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.appstertech.tempmonitor.BaseFragment;
import com.appstertech.tempmonitor.R;
import com.appstertech.tempmonitor.database.SharedPrefUtils;
import com.appstertech.tempmonitor.service.RetrofitManager;
import com.appstertech.tempmonitor.service.TempMonitorService;
import com.appstertech.tempmonitor.service.model.RefridgeGson;
import com.appstertech.tempmonitor.service.model.TempLogGson;
import com.appstertech.tempmonitor.ui.detail.DetailActivity;
import com.appstertech.tempmonitor.ui.home.refrigerator.RefridgeRecyclerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.recyclerview_home_refrigerator)
    RecyclerView mRecyclerRefridge;
    @BindView(R.id.swiperefreshlayout_home)
    SwipeRefreshLayout mRefreshLayout;

    private boolean isRequestingRefridge;

    private List<RefridgeGson> mDatas = new ArrayList<>();

    private RefridgeRecyclerAdapter mAdapter = new RefridgeRecyclerAdapter() {
        @Override
        public void onBindViewHolder(final RefridgeRecyclerAdapter.ViewHolder holder, int position) {
            if (null == mDatas || mDatas.isEmpty()) {
                return;
            }
            RefridgeGson data = mDatas.get(position);
            if (null == data) {
                return;
            }
            String openDoorStatus = data.getStatusOpenDoor();

            if (TextUtils.equals(openDoorStatus, "Not Install")) {
                //blue
                ViewCompat.setBackgroundTintList(holder.btnDoorStatus, ColorStateList.valueOf(ContextCompat.getColor(holder.btnDoorStatus.getContext(), R.color.blue_door_status_not_install)));
            } else if (TextUtils.equals(openDoorStatus, "Close")) {
                //green
                ViewCompat.setBackgroundTintList(holder.btnDoorStatus, ColorStateList.valueOf(ContextCompat.getColor(holder.btnDoorStatus.getContext(), R.color.green_door_status_close)));
            } else if (TextUtils.equals(openDoorStatus, "Open")) {
                //red
                ViewCompat.setBackgroundTintList(holder.btnDoorStatus, ColorStateList.valueOf(ContextCompat.getColor(holder.btnDoorStatus.getContext(), R.color.red_door_status_open)));

            }
            holder.btnDoorStatus.setText(data.getStatusOpenDoor());
            String tempStr = data.getTempIn();

            if (!TextUtils.isEmpty(tempStr)) {
                tempStr = tempStr.trim();
                try {
                    Float temp = Float.valueOf(tempStr);
                    Float maxRange = Float.valueOf(data.getMaxRang());
                    Float minRange = Float.valueOf(data.getMinRang());
                    if (maxRange >= temp && temp >= minRange) {
                        holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.blue_cool));
                    } else {
                        holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red_hot));
                    }
                } catch (Exception e) {
                    holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green_no_data));
                }

                holder.tvTemp.setText(tempStr + "ºC");
            } else {
                holder.tvTemp.setText("No Data !");
                holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green_no_data));
            }

            holder.imgMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null == mDatas || mDatas.isEmpty()) {
                        return;
                    }
                    RefridgeGson data = mDatas.get(holder.getAdapterPosition());
                    if (null == data) {
                        return;
                    }
                    popUpMenuMore(view, view.getContext(), data.getCode());
                }
            });

            holder.tvName.setText(data.getRefrigeratorName());
            if (!TextUtils.isEmpty(data.getRefrigeratorUseRangefrom()) || !TextUtils.isEmpty(data.getRefrigeratorUseRangeTo())) {
                holder.tvModelType.setText(data.getRefrigeratorUseRangefrom().trim() + " to " + data.getRefrigeratorUseRangeTo().trim() + " ºC");
                holder.tvModelType.setVisibility(View.VISIBLE);
            } else {
                holder.tvModelType.setVisibility(View.INVISIBLE);
            }


            if (!TextUtils.isEmpty(data.getRefrigeratorTempSetpoint())) {
                holder.btnTempSetPoint.setText(data.getRefrigeratorTempSetpoint().trim() + "ºC");
                holder.btnTempSetPoint.setVisibility(View.VISIBLE);
            } else {
                holder.btnTempSetPoint.setVisibility(View.INVISIBLE);
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null == mDatas || mDatas.isEmpty()) {
                        return;
                    }
                    RefridgeGson data = mDatas.get(holder.getAdapterPosition());
                    if (null == data) {
                        return;
                    }
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.KEY_DATA_REFRIDGE_GSON, data);
                    view.getContext().startActivity(intent);
                }
            });

        }


        @Override
        public int getItemCount() {
            if (null == mDatas || mDatas.isEmpty()) {
                return 0;
            }
            return mDatas.size();
        }
    };


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        mRecyclerRefridge.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerRefridge.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (null != SharedPrefUtils.getUser(getActivity())) {
                    requestRefridge(SharedPrefUtils.getUser(getActivity()).getOrganisationId(), getActivity());
                }

            }
        });
    }


    private void notifyRefridgeData() {
        mAdapter.notifyDataSetChanged();
    }

    private void popUpMenuMore(View anchor, final Context context, final String refridgeId) {
        PopupMenu popup = new PopupMenu(context, anchor);
        popup.getMenuInflater().inflate(R.menu.menu_item_more,
                popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_temp_history_log) {
                    requestTempLog(context, refridgeId);
                    return true;
                }
                return false;
            }
        });
        popup.show();
    }

    private void requestTempLog(final Context context, String refridgeId) {
        Call<List<TempLogGson>> call = RetrofitManager.build().create(TempMonitorService.class).getTempLogById(refridgeId);
        showLoading();
        call.enqueue(new Callback<List<TempLogGson>>() {
            @Override
            public void onResponse(Call<List<TempLogGson>> call, Response<List<TempLogGson>> response) {

                dismissLoading();
                if (response.isSuccessful()) {
                    if (null != context) {
                        showMoreTempHistory(response.body(), context);
                    }
                } else {
                    Toast.makeText(context, "requestTempLog failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<TempLogGson>> call, Throwable t) {

                dismissLoading();
                Toast.makeText(context, "requestTempLog failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private HashMap<String, RefridgeGson> mMapDatas = new HashMap<>();

//    private HashMap<String,RefridgeGson> parseToMap(List<RefridgeGson> datas){
//        HashMap<String,RefridgeGson> mapDatas = new HashMap<>();
//        for(RefridgeGson data : datas){
//            mapDatas.put(data.get)
//        }
//    }


    private void showMoreTempHistory(List<TempLogGson> datas, final Context context) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        builderSingle.setTitle("Temperature History Log");

        final ArrayAdapter<TempLogGson> arrayAdapter = new ArrayAdapter<TempLogGson>(
                context,
                R.layout.view_temperature_history_log, R.id.textview_temperature_history_log_text, datas) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                TempLogGson data = getItem(position);
                String displayStr = "Humid : " + data.getHumid() + " \n"
                        + "TempIn : " + data.getTempIn() + " \n"
                        + "TempOut : " + data.getTempOut() + " \n"
                        + "Time : " + data.getDateTime();

                tv.setText(displayStr);
                return tv;
            }
        };


        builderSingle.setAdapter(
                arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builderSingle.show();
    }

    private void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    private void dismissLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    private void requestRefridge(String UID, final Activity activity) {
        showLoading();
        Retrofit retrofit = RetrofitManager.build();
        TempMonitorService service = retrofit.create(TempMonitorService.class);
        Call<List<RefridgeGson>> call = service.getRefrigerator(String.valueOf(0));
        //TODO change back to UID
        isRequestingRefridge = true;
        call.enqueue(new Callback<List<RefridgeGson>>() {
            @Override
            public void onResponse(Call<List<RefridgeGson>> call, Response<List<RefridgeGson>> response) {
                isRequestingRefridge = false;
                dismissLoading();
                if (response.isSuccessful()) {
                    mDatas.clear();
                    mDatas.addAll(response.body());
                    if (null != activity && !activity.isFinishing()) {

                        notifyRefridgeData();
                    }
                } else {
                    Toast.makeText(activity, "requestRefridge failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<RefridgeGson>> call, Throwable t) {
                isRequestingRefridge = false;
                dismissLoading();
                Toast.makeText(activity, "requestRefridge failed", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != SharedPrefUtils.getUser(getActivity()) && mDatas.isEmpty() && SharedPrefUtils.getUser(getActivity()) != null) {
            requestRefridge(SharedPrefUtils.getUser(getActivity()).getOrganisationId(), getActivity());
        }

    }
}
