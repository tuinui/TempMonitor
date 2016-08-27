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

import java.sql.Ref;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        public void onBindViewHolder(final RecyclerView.ViewHolder genericHolder, int position) {
            if (null == mDatas || mDatas.isEmpty()) {
                return;
            }
            RefridgeGson data = mDatas.get(position);
            if (null == data) {
                return;
            }
            if (/*getItemViewType(position) == VIEW_TYPE_DATA && */genericHolder instanceof ViewHolder) {
                final ViewHolder dataHolder = (ViewHolder) genericHolder;
                String openDoorStatus = data.getStatusOpenDoor();

                if (TextUtils.equals(openDoorStatus, "Not Install")) {
                    //blue
                    ViewCompat.setBackgroundTintList(dataHolder.btnDoorStatus, ColorStateList.valueOf(ContextCompat.getColor(dataHolder.btnDoorStatus.getContext(), R.color.blue_door_status_not_install)));
                } else if (TextUtils.equals(openDoorStatus, "Close")) {
                    //green
                    ViewCompat.setBackgroundTintList(dataHolder.btnDoorStatus, ColorStateList.valueOf(ContextCompat.getColor(dataHolder.btnDoorStatus.getContext(), R.color.green_door_status_close)));
                } else if (TextUtils.equals(openDoorStatus, "Open")) {
                    //red
                    ViewCompat.setBackgroundTintList(dataHolder.btnDoorStatus, ColorStateList.valueOf(ContextCompat.getColor(dataHolder.btnDoorStatus.getContext(), R.color.red_door_status_open)));

                }
                dataHolder.btnDoorStatus.setText(data.getStatusOpenDoor());
                String tempStr = data.getTempIn();

                if (!TextUtils.isEmpty(tempStr)) {
                    tempStr = tempStr.trim();
                    try {
                        Float temp = Float.valueOf(tempStr);
                        Float maxRange = Float.valueOf(data.getMaxRang());
                        Float minRange = Float.valueOf(data.getMinRang());
                        if (maxRange >= temp && temp >= minRange) {
                            dataHolder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(dataHolder.itemView.getContext(), R.color.blue_cool));
                        } else {
                            dataHolder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(dataHolder.itemView.getContext(), R.color.red_hot));
                        }
                    } catch (Exception e) {
                        dataHolder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(dataHolder.itemView.getContext(), R.color.green_no_data));
                    }

                    dataHolder.tvTemp.setText(tempStr + "ºC");
                } else {
                    dataHolder.tvTemp.setText("No Data !");
                    dataHolder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(dataHolder.itemView.getContext(), R.color.green_no_data));
                }

                dataHolder.imgMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null == mDatas || mDatas.isEmpty()) {
                            return;
                        }
                        RefridgeGson data = mDatas.get(dataHolder.getAdapterPosition());
                        if (null == data) {
                            return;
                        }
                        popUpMenuMore(view, view.getContext(), data.getCode());
                    }
                });

                dataHolder.tvName.setText(data.getRefrigeratorName());
                if (!TextUtils.isEmpty(data.getRefrigeratorUseRangefrom()) || !TextUtils.isEmpty(data.getRefrigeratorUseRangeTo())) {
                    dataHolder.tvModelType.setText(data.getRefrigeratorUseRangefrom().trim() + " to " + data.getRefrigeratorUseRangeTo().trim() + " ºC");
                    dataHolder.tvModelType.setVisibility(View.VISIBLE);
                } else {
                    dataHolder.tvModelType.setVisibility(View.INVISIBLE);
                }


                if (!TextUtils.isEmpty(data.getRefrigeratorTempSetpoint())) {
                    dataHolder.btnTempSetPoint.setText(data.getRefrigeratorTempSetpoint().trim() + "ºC");
                    dataHolder.btnTempSetPoint.setVisibility(View.VISIBLE);
                } else {
                    dataHolder.btnTempSetPoint.setVisibility(View.INVISIBLE);
                }


                dataHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null == mDatas || mDatas.isEmpty()) {
                            return;
                        }
                        RefridgeGson data = mDatas.get(dataHolder.getAdapterPosition());
                        if (null == data) {
                            return;
                        }
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra(DetailActivity.KEY_DATA_REFRIDGE_GSON, data);
                        view.getContext().startActivity(intent);
                    }
                });

            } else if (genericHolder instanceof HeaderViewHolder) {
                HeaderViewHolder headerVH = (HeaderViewHolder) genericHolder;
                headerVH.tvHeader.setText(data.getHeaderLocationViewTitle());
            }


        }


        @Override
        public int getItemViewType(int position) {
            if (mDatas == null || mDatas.isEmpty()) {
                return super.getItemViewType(position);
            }

            if (!TextUtils.isEmpty(mDatas.get(position).getHeaderLocationViewTitle())) {
                return RefridgeRecyclerAdapter.VIEW_TYPE_HEADER;
            } else {
                return RefridgeRecyclerAdapter.VIEW_TYPE_DATA;
            }

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

//    private HashMap<String, List<RefridgeGson>> mMapDatas = new HashMap<>();


    private List<RefridgeGson> parseToViewModel(List<RefridgeGson> chatGson) {
        List<RefridgeGson> datas = new ArrayList<>();

        LinkedHashMap<String, List<RefridgeGson>> map = parseToMap(chatGson);

        for (String key : map.keySet()) {
            datas.addAll(map.get(key));
        }
        return datas;
    }

    private LinkedHashMap<String, List<RefridgeGson>> parseToMap(List<RefridgeGson> datas) {
        LinkedHashMap<String, List<RefridgeGson>> map = new LinkedHashMap<>();
        if (null == datas) {
            return map;
        }
        for (RefridgeGson data : datas) {

            String locationName = data.getLocationName();
            if (map.containsKey(locationName)) {
                for (RefridgeGson data2 : datas) {
                    if (TextUtils.equals(data2.getLocationName(), locationName)) {
                        map.get(locationName).add(data2);
                    }
                }
            } else {
                List<RefridgeGson> list = new ArrayList<>();
                RefridgeGson header = new RefridgeGson();
                header.setHeaderLocationViewTitle(data.getLocationName());
                list.add(header);
                list.add(data);
                map.put(locationName, list);
            }
        }
        return map;
    }

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
        Call<List<RefridgeGson>> call = service.getRefrigerator(UID);
        //TODO change back to UID
        isRequestingRefridge = true;
        call.enqueue(new Callback<List<RefridgeGson>>() {
            @Override
            public void onResponse(Call<List<RefridgeGson>> call, Response<List<RefridgeGson>> response) {
                isRequestingRefridge = false;
                dismissLoading();
                if (response.isSuccessful()) {
                    mDatas.clear();
                    mDatas.addAll(parseToViewModel(response.body()));
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
