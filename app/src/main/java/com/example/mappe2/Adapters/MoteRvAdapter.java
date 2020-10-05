package com.example.mappe2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mappe2.Fragments.MoteFragment;
import com.example.mappe2.Fragments.MoteInfo;
import com.example.mappe2.Modul.Mote;
import com.example.mappe2.MoteActivity;
import com.example.mappe2.R;
import java.util.List;

public class MoteRvAdapter extends RecyclerView.Adapter<MoteRvAdapter.ViewHolder> {

    private static ClickListener clickListener;
    Context mContext;

    private List<Mote> moteList;

    public MoteRvAdapter(Context context, List<Mote> list){
        this.mContext = context;
        moteList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.items_mote,null,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Mote mote = moteList.get(position);
        holder.navn.setText(mote.getNavn());
        holder.type.setText(mote.getType());
        holder.sted.setText(mote.getSted());
        holder.dato.setText(mote.getDato());
        holder.imageView.setImageResource(mote.getImg());
    }

    @Override
    public int getItemCount() {
        return moteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{
        TextView navn, type, sted, dato;
        ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            navn = itemView.findViewById(R.id.navn);
            type = itemView.findViewById(R.id.type);
            sted = itemView.findViewById(R.id.sted);
            dato = itemView.findViewById(R.id.dato);
            imageView = itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Mote mote = moteList.get(position);
            if(itemView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

                Intent intent = new Intent(mContext, MoteActivity.class);

                intent.putExtra("navn", mote.getNavn());
                intent.putExtra("type", mote.getType());
                intent.putExtra("sted", mote.getSted());
                intent.putExtra("dato", mote.getDato());

                mContext.startActivity(intent);
            }

        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(), view);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MoteRvAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
