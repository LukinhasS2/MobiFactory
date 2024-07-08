package com.pucpr.mobifactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductionTrackingActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPedidos;
    private PedidoAdapter pedidoAdapter;
    private DatabaseHelper dbHelper;
    private Button buttonNovoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_tracking);

        recyclerViewPedidos = findViewById(R.id.recyclerViewPedidos);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        List<Pedido> listaPedidos = dbHelper.getAllPedidos();

        pedidoAdapter = new PedidoAdapter(listaPedidos);
        recyclerViewPedidos.setAdapter(pedidoAdapter);

        buttonNovoPedido = findViewById(R.id.buttonNovoPedido);
        buttonNovoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ação ao clicar no botão "Novo Pedido"
                Toast.makeText(ProductionTrackingActivity.this, "Clicou em Novo Pedido", Toast.LENGTH_SHORT).show();
                // Exemplo de navegação para uma nova activity
                Intent intent = new Intent(ProductionTrackingActivity.this, AddPedidoActivity.class);
                startActivity(intent);
            }
        });
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
            holder.textViewPedidoId.setText(pedido.getId());
            holder.textViewCliente.setText(pedido.getCliente());
            holder.textViewDataEntrega.setText(pedido.getDataEntrega());
            holder.textViewStatus.setText(pedido.getStatus());

            holder.buttonDetalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProductionTrackingActivity.this, "Detalhes do pedido " + pedido.getId(), Toast.LENGTH_SHORT).show();
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