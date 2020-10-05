package com.example.mappe2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mappe2.Modul.Mote;
import com.example.mappe2.Modul.Person;
import com.example.mappe2.MoteActivity;
import com.example.mappe2.PersonActivity;
import com.example.mappe2.R;
import java.util.ArrayList;
import java.util.List;

public class PersonRvAdapter extends RecyclerView.Adapter<PersonRvAdapter.ViewHolder> {

    private static ClickListener clickListener;
    Context mContext;

    private List<Person> mpersonList;

    public PersonRvAdapter(Context context, List<Person> listPerson){
        this.mContext = context;
        mpersonList = listPerson;
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

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{

        TextView navn, telefonnr;
        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            navn = itemView.findViewById(R.id.person);
            telefonnr = itemView.findViewById(R.id.telefonnr);
            imageView = itemView.findViewById(R.id.imageView1);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Person person = mpersonList.get(position);
            Intent intent = new Intent(mContext, PersonActivity.class);

            intent.putExtra("navn2", person.getNavn());
            intent.putExtra("telefonnr2", person.getTelefonnr());

            mContext.startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(), view);
            return false;
        }
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }


}
