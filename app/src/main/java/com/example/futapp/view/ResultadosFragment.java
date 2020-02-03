package com.example.futapp.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.futapp.AppFragment;
import com.example.futapp.R;
import com.example.futapp.model.Resultado;

import java.util.List;


public class ResultadosFragment extends AppFragment {

    public ResultadosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_resultados, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ResultadosAdapter resultadosAdapter = new ResultadosAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.resultadosRecyclerView);

        recyclerView.setAdapter(resultadosAdapter);

        appViewModel.obtenerResultados().observe(getViewLifecycleOwner(), new Observer<List<Resultado>>() {
            @Override
            public void onChanged(List<Resultado> resultados) {
                resultadosAdapter.setResultadoList(resultados);
            }
        });
    }

    class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ResultadoViewHolder> {

        List<Resultado> resultadoList;


        @NonNull
        @Override
        public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ResultadoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_resultado, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
            Resultado resultado = resultadoList.get(position);

            holder.button.setText(resultado.equipo1 + " " +resultado.goles1 + " - " + resultado.goles2 + " " + resultado.equipo2);
        }

        @Override
        public int getItemCount() {
            return resultadoList == null ? 0 : resultadoList.size();
        }

        public void setResultadoList(List<Resultado> resultadoList) {
            this.resultadoList = resultadoList;
            notifyDataSetChanged();
        }

        class ResultadoViewHolder extends RecyclerView.ViewHolder {

            Button button;

            public ResultadoViewHolder(@NonNull View itemView) {
                super(itemView);

                button = itemView.findViewById(R.id.resultado);
            }
        }
    }
}