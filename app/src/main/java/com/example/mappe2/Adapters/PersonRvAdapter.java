package com.example.mappe2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mappe2.Modul.Person;
import com.example.mappe2.R;
import com.example.mappe2.RecyclerViewInterface;
import java.util.List;

public class PersonRvAdapter extends RecyclerView.Adapter<PersonRvAdapter.ViewHolder> {

    private RecyclerViewInterface recyclerViewInterface;
    Context mContext;
    private List<Person> mpersonList;

    public PersonRvAdapter(Context context, List<Person> listPerson, RecyclerViewInterface recyclerViewInterface){
        this.mContext = context;
        mpersonList = listPerson;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_person,null,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Person person = mpersonList.get(position);
        holder.navn.setText(person.getNavn());
        holder.telefonnr.setText(person.getTelefonnr());
        holder.imageView.setImageResource(person.getImg());

    }

    @Override
    public int getItemCount() {
        return mpersonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView navn, telefonnr;
        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            navn = itemView.findViewById(R.id.person);
            telefonnr = itemView.findViewById(R.id.telefonnr);
            imageView = itemView.findViewById(R.id.imageView1);

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
