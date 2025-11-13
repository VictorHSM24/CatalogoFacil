package com.example.catalogofacil.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.catalogofacil.R;
import com.example.catalogofacil.adapter.PedidoAdapter; // Importa o PedidoAdapter customizado
import com.example.catalogofacil.model.Pedido; // Importa a classe Pedido

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class VisualizarPedidosActivity extends AppCompatActivity {

    private ListView listViewPedidos;
    private Button buttonVoltarInicialVisualizar;

    private DatabaseReference databasePedidos;
    private List<Pedido> pedidoList;
    private PedidoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_pedidos);

        databasePedidos = FirebaseDatabase.getInstance().getReference("pedidos");

        listViewPedidos = findViewById(R.id.listViewPedidos);
        buttonVoltarInicialVisualizar = findViewById(R.id.buttonVoltarInicialVisualizar);
        pedidoList = new ArrayList<>();

        buttonVoltarInicialVisualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }//onCreate

    @Override
    protected void onStart() {
        super.onStart();

        databasePedidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pedidoList.clear();

                for (DataSnapshot pedidoSnapshot : dataSnapshot.getChildren()) {
                    Pedido pedido = pedidoSnapshot.getValue(Pedido.class);
                    if (pedido != null) {
                        pedidoList.add(pedido);
                    }
                }

                adapter = new PedidoAdapter(VisualizarPedidosActivity.this, pedidoList);
                listViewPedidos.setAdapter(adapter);

                if (pedidoList.isEmpty()) {
                    Toast.makeText(VisualizarPedidosActivity.this, "Nenhum pedido cadastrado.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Erro ao carregar dados: " + databaseError.getMessage());
                Toast.makeText(VisualizarPedidosActivity.this, "Erro ao carregar pedidos.", Toast.LENGTH_LONG).show();
            }
        });
    }//onStart
}