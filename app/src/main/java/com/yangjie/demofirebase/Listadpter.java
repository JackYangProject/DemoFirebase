package com.yangjie.demofirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Listadpter extends  RecyclerView.Adapter<Listadpter.ViewHolder>{
    private List<Member> members = new ArrayList<>();
    private Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Member myaqi = members.get(position);
        holder.sitename.setText(myaqi.getName());
        holder.email.setText(myaqi.getEmail());
        holder.password.setText(myaqi.getPassword());
        holder.id.setText(myaqi.getId());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView sitename,email,password,id;

        ViewHolder(View itemView) {
            super(itemView);
            sitename = itemView.findViewById(R.id.sitename);
            email = itemView.findViewById(R.id.email);
            password = itemView.findViewById(R.id.password);
            id = itemView.findViewById(R.id.id);
        }
    }
}
