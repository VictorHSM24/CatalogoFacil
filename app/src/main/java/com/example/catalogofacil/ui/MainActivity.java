package com.example.catalogofacil.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.catalogofacil.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonCadastrarPedido;
    private Button buttonVisualizarPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCadastrarPedido = findViewById(R.id.buttonCadastrarPedido);
        buttonVisualizarPedidos = findViewById(R.id.buttonVisualizarPedidos);

        buttonCadastrarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroPedidoActivity.class);
                startActivity(intent);
            }
        });

        buttonVisualizarPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VisualizarPedidosActivity.class);
                startActivity(intent);
            }
        });
    }
}