package com.example.rollcallhust.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.listeners.ItemClickListener;
import com.example.rollcallhust.models.Class;
import com.example.rollcallhust.views.activities.ClassDetailActivity;

import org.w3c.dom.Text;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ClassViewHolder> {
    List<Class> datas;
    Context context;
    public RVAdapter(List<Class> datas, Context context){
        this.datas = datas;
        this.context = context;
    }
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.class_cardview, viewGroup, false);
        return new ClassViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder classViewHolder, int i) {
        final Class studyClass = datas.get(i);
        classViewHolder.tvPriority.setText(String.valueOf(i+1));
        classViewHolder.tvClassCode.setText("Mã lớp: " + studyClass.getClassCode());
        classViewHolder.tvClassName.setText(studyClass.getClassName());
        classViewHolder.tvClassSize.setText("Sĩ số: " + studyClass.getSize());
//        noteViewHolder.tvTitleNote.setText(note.getTitle());
//        noteViewHolder.tvDescription.setText(note.getDescription());
//        noteViewHolder.tvDate.setText(note.getDate());
//        noteViewHolder.tvContent.setText(note.getContent());
//        noteViewHolder.tvPriority.setText("" + (i+1));
        classViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                    String classCode = datas.get(position).getClassCode();
                    Intent intent = new Intent(context, ClassDetailActivity.class);
                    intent.putExtra("classCode", classCode);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private ItemClickListener itemClickListener;

        TextView tvPriority;
        TextView tvClassCode;
        TextView tvClassName;
        TextView tvClassSize;

        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        ClassViewHolder(View itemView) {
            super(itemView);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvClassCode = itemView.findViewById(R.id.tvClassCode);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvClassSize = itemView.findViewById(R.id.tvClassSize);
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
