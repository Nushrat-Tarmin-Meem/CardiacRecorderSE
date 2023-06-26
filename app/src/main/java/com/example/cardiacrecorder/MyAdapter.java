package com.example.cardiacrecorder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Records> list;
    Integer a,b,c;

    public MyAdapter(Context context, ArrayList<Records> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Records records=list.get(position);
        holder.name.setText(records.getMail());
        holder.sistolic.setText(records.getSis());
        holder.diastolic.setText(records.getDias());
        holder.hrate.setText(records.getRate());
        holder.comments.setText(records.getComment());
        holder.date.setText(records.getD());
        holder.time.setText(records.getT());
        a=Integer.valueOf(holder.sistolic.getText().toString());
        b=Integer.valueOf(holder.diastolic.getText().toString());
        c=Integer.valueOf(holder.hrate.getText().toString());
        if(a<70)
        {
            holder.sistolic.setTextColor(Color.parseColor("#FF0000"));
        }
        if(b>120)
        {
            holder.diastolic.setTextColor(Color.parseColor("#FF0000"));
        }
        if(c>=70 && c<=90)
        {
            holder.hrate.setTextColor(Color.parseColor("#22913E"));
        }
        else {
            holder.hrate.setTextColor(Color.parseColor("#FF0000"));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name,sistolic,diastolic,hrate,comments,date,time;
        Button u,d;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.iname);
            sistolic=itemView.findViewById(R.id.isis);
            diastolic=itemView.findViewById(R.id.idias);
            hrate=itemView.findViewById(R.id.irate);
            comments=itemView.findViewById(R.id.icomment);
            date=itemView.findViewById(R.id.idate);
            time =itemView.findViewById(R.id.itime);
            u=itemView.findViewById(R.id.update);
            d=itemView.findViewById(R.id.delete);
            u.setOnClickListener(this);
            d.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }
}
