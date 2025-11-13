package com.example.catalogofacil.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.catalogofacil.R;
import com.example.catalogofacil.model.Pedido;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity {

    // Componentes de UI
    private EditText editCodigoRoupa, editNomeRoupa, editQuantidade, editNomeCliente, editTelefone;
    private Spinner spinnerCor, spinnerTamanho, spinnerSituacaoPagamento;
    private Button buttonSalvarPedido, buttonVoltarInicial;

    private DatabaseReference databasePedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        databasePedidos = FirebaseDatabase.getInstance().getReference("pedidos");

        editCodigoRoupa = findViewById(R.id.editCodigoRoupa);
        editNomeRoupa = findViewById(R.id.editNomeRoupa);
        editQuantidade = findViewById(R.id.editQuantidade);
        editNomeCliente = findViewById(R.id.editNomeCliente);
        editTelefone = findViewById(R.id.editTelefone);

        spinnerCor = findViewById(R.id.spinnerCor);
        spinnerTamanho = findViewById(R.id.spinnerTamanho);
        spinnerSituacaoPagamento = findViewById(R.id.spinnerSituacaoPagamento);

        buttonSalvarPedido = findViewById(R.id.buttonSalvarPedido);
        buttonVoltarInicial = findViewById(R.id.buttonVoltarInicial);

        setupSpinners();

        buttonSalvarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPedido();
            }
        });
        buttonVoltarInicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }//onCreate

    private void setupSpinners() {
        List<String> cores = new ArrayList<>();
        cores.add("Selecione a Cor");
        cores.add("Branco");
        cores.add("Preto");
        cores.add("Vermelho");
        cores.add("Azul");
        cores.add("Outra...");
        ArrayAdapter<String> corAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cores);
        corAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCor.setAdapter(corAdapter);

        List<String> tamanhos = new ArrayList<>();
        tamanhos.add("Selecione o Tamanho");
        tamanhos.add("XG");
        tamanhos.add("GG");
        tamanhos.add("G");
        tamanhos.add("M");
        tamanhos.add("P");
        tamanhos.add("PP");
        ArrayAdapter<String> tamanhoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamanhos);
        tamanhoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTamanho.setAdapter(tamanhoAdapter);

        List<String> situacoes = new ArrayList<>();
        situacoes.add("Selecione a Situação");
        situacoes.add("Pago");
        situacoes.add("Não Pago");
        ArrayAdapter<String> situacaoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, situacoes);
        situacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSituacaoPagamento.setAdapter(situacaoAdapter);
    }//setupSpinners

    private void salvarPedido() {
        String codigo = editCodigoRoupa.getText().toString().trim();
        String nomeRoupa = editNomeRoupa.getText().toString().trim();
        String quantidadeStr = editQuantidade.getText().toString().trim();
        String nomeCliente = editNomeCliente.getText().toString().trim();
        String telefone = editTelefone.getText().toString().trim();

        String cor = spinnerCor.getSelectedItem().toString();
        String tamanho = spinnerTamanho.getSelectedItem().toString();
        String situacaoPagamento = spinnerSituacaoPagamento.getSelectedItem().toString();

        if (TextUtils.isEmpty(codigo) || TextUtils.isEmpty(nomeRoupa) || TextUtils.isEmpty(quantidadeStr) ||
                TextUtils.isEmpty(nomeCliente) || TextUtils.isEmpty(telefone)) {
            Toast.makeText(this, "Por favor, preencha todos os campos de texto.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cor.equals("Selecione a Cor") || tamanho.equals("Selecione o Tamanho") || situacaoPagamento.equals("Selecione a Situação")) {
            Toast.makeText(this, "Por favor, selecione as opções nos menus.", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantidade;
        try {
            quantidade = Integer.parseInt(quantidadeStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Quantidade deve ser um número válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pedido pedido = new Pedido(
                codigo,
                nomeRoupa,
                cor,
                tamanho,
                quantidade,
                nomeCliente,
                telefone,
                situacaoPagamento
        );

        String pedidoId = databasePedidos.push().getKey();
        if (pedidoId != null) {
            databasePedidos.child(pedidoId).setValue(pedido);
            Toast.makeText(this, "Pedido salvo com sucesso!", Toast.LENGTH_LONG).show();

            clearFields();
        } else {
            Toast.makeText(this, "Erro ao gerar ID do pedido. Tente novamente.", Toast.LENGTH_LONG).show();
        }
    }//salvarPedido

    private void clearFields() {
        editCodigoRoupa.setText("");
        editNomeRoupa.setText("");
        editQuantidade.setText("");
        editNomeCliente.setText("");
        editTelefone.setText("");

        spinnerCor.setSelection(0);
        spinnerTamanho.setSelection(0);
        spinnerSituacaoPagamento.setSelection(0);
    }//clearFields
}