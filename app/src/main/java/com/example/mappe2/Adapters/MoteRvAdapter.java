package com.example.mappe2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mappe2.Modul.Mote;
import com.example.mappe2.R;
import com.example.mappe2.RecyclerViewInterface;
import java.util.List;

public class MoteRvAdapter extends RecyclerView.Adapter<MoteRvAdapter.ViewHolder> {

    private RecyclerViewInterface recyclerViewInterface;
    Context mContext;
    public List<Mote> moteList;

    public MoteRvAdapter(Context context, List<Mote> list, RecyclerViewInterface recyclerViewInterface){
        this.mContext = context;
        moteList = list;
        this.recyclerViewInterface = recyclerViewInterface;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView navn, type, sted, dato;
        ImageView imageView;
        public ViewHolder(View itemView){
            super(itemView);
            navn = itemView.findViewById(R.id.navn);
            type = itemView.findViewById(R.id.type);
            sted = itemView.findViewById(R.id.sted);
            dato = itemView.findViewById(R.id.dato);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewInterface.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    recyclerViewInterface.onLongItemClick(getAdapterPosition());
                    return true;
                }

            });

        }

    }

}
