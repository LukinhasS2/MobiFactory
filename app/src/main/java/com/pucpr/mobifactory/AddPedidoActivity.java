package com.pucpr.mobifactory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPedidoActivity extends AppCompatActivity {

    private EditText editTextCliente, editTextDataEntrega, editTextStatus;
    private Button buttonAddPedido;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pedido);

        dbHelper = new DatabaseHelper(this);

        editTextCliente = findViewById(R.id.editTextCliente);
        editTextDataEntrega = findViewById(R.id.editTextDataEntrega);
        editTextStatus = findViewById(R.id.editTextStatus);
        buttonAddPedido = findViewById(R.id.buttonAddPedido);

        buttonAddPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cliente = editTextCliente.getText().toString();
                String dataEntrega = editTextDataEntrega.getText().toString();
                String status = editTextStatus.getText().toString();

                if (cliente.isEmpty() || dataEntrega.isEmpty() || status.isEmpty()) {
                    Toast.makeText(AddPedidoActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    Pedido novoPedido = new Pedido(null, cliente, dataEntrega, status);
                    dbHelper.addPedido(novoPedido);
                    Toast.makeText(AddPedidoActivity.this, "Pedido adicionado com sucesso", Toast.LENGTH_SHORT).show();
                    finish(); // Voltar para a atividade anterior
                }
            }
        });
    }
}