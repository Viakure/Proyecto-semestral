package com.example.proyectocm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectocm.models.naturaleza;

import java.util.ArrayList;

public class ListaNaturalezaAdapter extends RecyclerView.Adapter<ListaNaturalezaAdapter.ViewHolder> {

    private ArrayList<naturaleza> dataset;
    private Context context;

    public ListaNaturalezaAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<naturaleza>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_naturaleza, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        naturaleza n = dataset.get(position);
        holder.tvNaturaleza.setText(n.getName());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaNaturaleza(ArrayList<naturaleza> listaNaturaleza) {
        dataset.addAll(listaNaturaleza);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNaturaleza;

        public ViewHolder(View itemView){
            super(itemView);
            tvNaturaleza = (TextView) itemView.findViewById(R.id.tvNaturaleza);
        }
    }
}
