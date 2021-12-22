package id.adidharmawati.diyzone.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.adidharmawati.diyzone.EditActivity;
import id.adidharmawati.diyzone.IndexActivity;
import id.adidharmawati.diyzone.LoginActivity;
import id.adidharmawati.diyzone.R;
import id.adidharmawati.diyzone.ViewActivity;
import id.adidharmawati.diyzone.database.AppDatabase;
import id.adidharmawati.diyzone.database.entity.Tutorial;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.ViewAdapter>{

    private AppDatabase database;
    private List<Tutorial> list;
    private Context context;

    public TutorialAdapter(Context context, List<Tutorial> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tutorial,parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTitle.setText(list.get(position).judul);
        holder.conTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent (view.getContext(),ViewActivity.class);
                int id = list.get(position).id_tutorial;
                viewIntent.putExtra("id",id);
                context.startActivity(viewIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ConstraintLayout conTutorial;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_judul);
            conTutorial = itemView.findViewById(R.id.container_list);
        }
    }

    public void filterList (List<Tutorial> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

}
