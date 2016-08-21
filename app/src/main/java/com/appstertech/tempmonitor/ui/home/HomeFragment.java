package com.appstertech.tempmonitor.ui.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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


    private RefridgeRecyclerAdapter mAdapter;

    private boolean isRequestingRefridge;

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
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestRefridge(SharedPrefUtils.getUser(getActivity()).getUserId(), getActivity());
            }
        });
    }

    private void notifyRefridgeData(final List<RefridgeGson> datas) {
        mAdapter = new RefridgeRecyclerAdapter() {
            @Override
            public void onBindViewHolder(final ViewHolder holder, int position) {
                RefridgeGson data = datas.get(position);
                holder.btnDoorStatus.setText("Door close");
                String tempStr = data.getRefrigeratorPMCALInterval();

                if (!TextUtils.isEmpty(tempStr)) {
                    if (TextUtils.isDigitsOnly(tempStr)) {
                        int temp = Integer.valueOf(tempStr);
                        if (temp > 5) {
                            holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red_hot));
                        } else {
                            holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.blue_cool));
                        }

                    } else {
                        holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green_no_data));
                    }
                    holder.tvTemp.setText(data.getRefrigeratorPMCALInterval() + "ºC");
                } else {
                    holder.tvTemp.setText("No Data !");
                    holder.cardRoot.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green_no_data));
                }

                holder.imgMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RefridgeGson data = datas.get(holder.getAdapterPosition());
                        popUpMenuMore(view, view.getContext(), data.getCode());
                    }
                });

                holder.tvName.setText(data.getRefrigeratorName());
                holder.tvModelType.setText(data.getTypeName());

                holder.btnTempSetPoint.setText(data.getRefrigeratorTempSetpoint() + "ºC");

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra(DetailActivity.KEY_DATA_REFRIDGE_GSON, datas.get(holder.getAdapterPosition()));
                        view.getContext().startActivity(intent);
                    }
                });

            }


            @Override
            public int getItemCount() {
                return datas.size();
            }
        };
        mRecyclerRefridge.setAdapter(mAdapter);
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
//        ProgressDialog dialog = ProgressDialog.show(activity,"",activity.getString(R.string.Loading));
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


    private void showMoreTempHistory(List<TempLogGson> datas, final Context context) {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
        builderSingle.setTitle("Temperature History Log");

        final ArrayAdapter<TempLogGson> arrayAdapter = new ArrayAdapter<TempLogGson>(
                context,
                android.R.layout.simple_list_item_1, datas){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent).findViewById(android.R.id.text1);
                TempLogGson data=   getItem(position);
                String displayStr = "Humid : " + data.getHumid() + " \n"
                        + "TempIn : " + data.getTempIn() + " \n"
                        + "TempOut : " + data.getTempOut() + " \n"
                        + "Time : " + data.getDateTime() + " \n";

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
        isRequestingRefridge = true;
        call.enqueue(new Callback<List<RefridgeGson>>() {
            @Override
            public void onResponse(Call<List<RefridgeGson>> call, Response<List<RefridgeGson>> response) {
                isRequestingRefridge = false;
                dismissLoading();
                if (response.isSuccessful()) {
                    if (null != activity && !activity.isFinishing()) {
                        notifyRefridgeData(response.body());
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
        requestRefridge(SharedPrefUtils.getUser(getActivity()).getUserId(), getActivity());
    }
}
