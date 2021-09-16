package com.example.rollcallhust.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.listeners.ItemClickListener;
import com.example.rollcallhust.models.Class;
import com.example.rollcallhust.models.RollCall;
import com.example.rollcallhust.views.activities.ClassDetailActivity;
import com.example.rollcallhust.views.activities.RollCallDetailActivity;

import java.util.List;

public class RVClassDetailAdapter extends RecyclerView.Adapter<RVClassDetailAdapter.ClassDetailViewHolder> {
    List<RollCall> datas;
    Context context;
    public RVClassDetailAdapter(List<RollCall> datas, Context context){
        this.datas = datas;
        this.context = context;
    }
    @NonNull
    @Override
    public ClassDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.class_detail_cardview, viewGroup, false);
        return new ClassDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassDetailViewHolder classDetailViewHolder, int i) {
        RollCall rollCall = datas.get(i);
//        noteViewHolder.tvTitleNote.setText(note.getTitle());
//        noteViewHolder.tvDescription.setText(note.getDescription());
//        noteViewHolder.tvDate.setText(note.getDate());
//        noteViewHolder.tvContent.setText(note.getContent());
        classDetailViewHolder.tvPriority.setText("" + (i+1));
        classDetailViewHolder.tvDateRollCall.setText(rollCall.getDate());
        classDetailViewHolder.tvTimeRollCall.setText(rollCall.getTime());
        classDetailViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    Intent intent = new Intent(context, RollCallDetailActivity.class);
                    intent.putExtra("rollCallId", datas.get(position).getRollCallId());
                    //Toast.makeText(context, datas.get(position).getRollCallId() + "", Toast.LENGTH_SHORT).show();
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ClassDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ItemClickListener itemClickListener;

        TextView tvPriority;
        TextView tvDateRollCall;
        TextView tvTimeRollCall;

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        ClassDetailViewHolder(View itemView) {
            super(itemView);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvDateRollCall = itemView.findViewById(R.id.tvDateRollCall);
            tvTimeRollCall = itemView.findViewById(R.id.tvTimeRollCall);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
