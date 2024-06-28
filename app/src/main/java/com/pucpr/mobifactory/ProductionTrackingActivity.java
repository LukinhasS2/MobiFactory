package com.pucpr.mobifactory;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductionTrackingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPedidos;
    private PedidoAdapter pedidoAdapter;
    private List<Pedido> listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_tracking);

        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));

        // Simular lista de pedidos
        listaPedidos = new ArrayList<>();
        listaPedidos.add(new Pedido("001", "Cliente A", "01/07/2024", "Corte"));
        listaPedidos.add(new Pedido("002", "Cliente B", "05/07/2024", "Montagem"));
        listaPedidos.add(new Pedido("003", "Cliente C", "10/07/2024", "Acabamento"));

        pedidoAdapter = new PedidoAdapter(listaPedidos);
        recyclerViewPedidos.setAdapter(pedidoAdapter);
    }

    // Classe Pedido
    public class Pedido {
        String id, cliente, dataEntrega, status;

        public Pedido(String id, String cliente, String dataEntrega, String status) {
            this.id = id;
            this.cliente = cliente;
            this.dataEntrega = dataEntrega;
            this.status = status;
        }
    }

    // Adapter para RecyclerView
    public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

        private List<Pedido> pedidos;

        public PedidoAdapter(List<Pedido> pedidos) {
            this.pedidos = pedidos;
        }

        @Override
        public PedidoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_pedido, parent, false);
            return new PedidoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PedidoViewHolder holder, int position) {
            Pedido pedido = pedidos.get(position);
            holder.textViewPedidoId.setText(pedido.id);
            holder.textViewCliente.setText(pedido.cliente);
            holder.textViewDataEntrega.setText(pedido.dataEntrega);
            holder.textViewStatus.setText(pedido.status);

            holder.buttonDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProductionTrackingActivity.this, "Detalhes do pedido " + pedido.id, Toast.LENGTH_SHORT).show();
                    // Implementar navegação para a tela de detalhes do pedido, se necessário
                }
            });
        }

        @Override
        public int getItemCount() {
            return pedidos.size();
        }

        public class PedidoViewHolder extends RecyclerView.ViewHolder {
            TextView textViewPedidoId, textViewCliente, textViewDataEntrega, textViewStatus;
            Button buttonDetalhes;

            public PedidoViewHolder(View itemView) {
                super(itemView);
                textViewPedidoId = itemView.findViewById(R.id.textViewPedidoId);
                textViewCliente = itemView.findViewById(R.id.textViewCliente);
                textViewDataEntrega = itemView.findViewById(R.id.textViewDataEntrega);
                textViewStatus = itemView.findViewById(R.id.textViewStatus);
                buttonDetalhes = itemView.findViewById(R.id.buttonDetalhes);
            }
        }
    }
}