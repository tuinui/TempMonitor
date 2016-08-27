package com.appstertech.tempmonitor.ui.home.refrigerator;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstertech.tempmonitor.R;
import com.appstertech.tempmonitor.utils.DrawableHelper;

/**
 * Created by nuimamon on 31/7/2559.
 */
public abstract class RefridgeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VIEW_TYPE_DATA = 2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header_refrigerator, parent, false));
        } else if (viewType == VIEW_TYPE_DATA) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_refrigerator, parent, false));
        } else {
            return new RecyclerView.ViewHolder(new View(parent.getContext())) {

            };
        }
//        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_refrigerator,parent,false));
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvHeader;

        public HeaderViewHolder(View v) {
            super(v);
            tvHeader = (TextView) v.findViewById(R.id.textview_header_refrigerator);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvName;
        public final Button btnDoorStatus, btnTempSetPoint;
        public final TextView tvTemp;
        public final TextView tvModelType;
        public final CardView cardRoot;
        public final ImageView imgMore;

        public ViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.textview_refrigerator_model_name);
            imgMore = (ImageView) v.findViewById(R.id.imageview_refrigerator_more);
            cardRoot = (CardView) v.findViewById(R.id.cardview_refrigerator_root);
            btnDoorStatus = (Button) v.findViewById(R.id.button_refrigerator_door_status);
            tvTemp = (TextView) v.findViewById(R.id.textview_refrigerator_temperature);
            tvModelType = (TextView) v.findViewById(R.id.textview_refrigerator_model_type);
            btnTempSetPoint = (Button) v.findViewById(R.id.button_refrigerator_temp_set_point);
//            ViewCompat.setBackgroundTintList(btnTempSetPoint, ColorStateList.valueOf(ContextCompat.getColor(v.getContext(),R.color.green_status)));
//            ViewCompat.setBackgroundTintList(btnDoorStatus, ColorStateList.valueOf(ContextCompat.getColor(v.getContext(),R.color.green_status)));

//            DrawableHelper.withContext(v.getContext()).withColor().applyTo(btnDoorStatus);
        }

        public Context getContext() {
            return itemView.getContext();
        }
    }
}
