package com.pucpr.mobifactory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DashboardActivity extends AppCompatActivity {

    private TextView textViewPedidosProducao, textViewPedidosAtrasados, textViewNivelEstoque;
    private Button buttonProducao, buttonEstoque, buttonComunicacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardactivity);

        textViewPedidosProducao = findViewById(R.id.textViewPedidosProducao);
        textViewPedidosAtrasados = findViewById(R.id.textViewPedidosAtrasados);
        textViewNivelEstoque = findViewById(R.id.textViewNivelEstoque);
        buttonProducao = findViewById(R.id.buttonProducao);
        buttonEstoque = findViewById(R.id.buttonEstoque);
        buttonComunicacao = findViewById(R.id.buttonComunicacao);

        // Pre encher os KPIs com valores aleatórios
        updateKPIs();

        textViewPedidosProducao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Produção clicada", Toast.LENGTH_SHORT).show();
                // Navegar para a tela de produção
                Intent intent = new Intent(DashboardActivity.this, ProductionTrackingActivity.class);
                startActivity(intent);
            }
        });

        textViewPedidosAtrasados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Pedidos atrasados clicados", Toast.LENGTH_SHORT).show();
                // Implemente aqui a navegação para a tela de pedidos atrasados, se necessário
            }
        });

        textViewNivelEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Nível de estoque clicado", Toast.LENGTH_SHORT).show();
                // Implemente aqui a navegação para a tela de nível de estoque, se necessário
            }
        });

        buttonProducao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Produção clicada", Toast.LENGTH_SHORT).show();
                // Navegar para a tela de produção
                Intent intent = new Intent(DashboardActivity.this, ProductionTrackingActivity.class);
                startActivity(intent);
            }
        });


        buttonEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Estoque clicado", Toast.LENGTH_SHORT).show();
                // Navegar para a tela de estoque
            }
        });

        buttonComunicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "Comunicação clicada", Toast.LENGTH_SHORT).show();
                // Navegar para a tela de comunicação
            }
        });
    }

    private void updateKPIs() {
        Random random = new Random();

        int pedidosProducao = random.nextInt(100); // Gera um número aleatório entre 0 e 99
        int pedidosAtrasados = random.nextInt(10); // Gera um número aleatório entre 0 e 9
        int nivelEstoque = random.nextInt(101); // Gera um número aleatório entre 0 e 100 (representando %)

        textViewPedidosProducao.setText(String.valueOf(pedidosProducao));
        textViewPedidosAtrasados.setText(String.valueOf(pedidosAtrasados));
        textViewNivelEstoque.setText(nivelEstoque + "%");
    }
}