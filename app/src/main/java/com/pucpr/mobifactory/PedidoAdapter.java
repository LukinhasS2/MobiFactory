package com.pucpr.mobifactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private Context context;
    private List<Pedido> pedidos;

    public PedidoAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);
        holder.textViewId.setText(pedido.getId());
        holder.textViewCliente.setText(pedido.getCliente());
        holder.textViewDataEntrega.setText(pedido.getDataEntrega());
        holder.textViewStatus.setText(pedido.getStatus());

        holder.buttonDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Detalhes do pedido " + pedido.getId(), Toast.LENGTH_SHORT).show();
                // Implementar navegação para a tela de detalhes do pedido, se necessário
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewId, textViewCliente, textViewDataEntrega, textViewStatus;
        Button buttonDetalhes;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewPedidoId);
            textViewCliente = itemView.findViewById(R.id.textViewCliente);
            textViewDataEntrega = itemView.findViewById(R.id.textViewDataEntrega);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            buttonDetalhes = itemView.findViewById(R.id.buttonDetalhes);
        }
    }
}