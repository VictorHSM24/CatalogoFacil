package com.example.catalogofacil.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.catalogofacil.R;
import com.example.catalogofacil.model.Pedido;

import java.util.List;
public class PedidoAdapter extends ArrayAdapter<Pedido>{

    private Activity context;
    private List<Pedido> pedidoList;

    public PedidoAdapter(Activity context, List<Pedido> pedidoList) {
        super(context, R.layout.list_item_pedido, pedidoList);
        this.context = context;
        this.pedidoList = pedidoList;
    }//PedidoAdapter

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = inflater.inflate(R.layout.list_item_pedido, null, true);
        }

        Pedido pedido = pedidoList.get(position);

        TextView tvCodigo = listItemView.findViewById(R.id.tvCodigo);
        tvCodigo.setText("Código: " + pedido.getCodigoRoupa());

        TextView tvNomeRoupa = listItemView.findViewById(R.id.tvNomeRoupa);
        tvNomeRoupa.setText("Roupa: " + pedido.getNomeRoupa());

        TextView tvDetalhesRoupa = listItemView.findViewById(R.id.tvDetalhesRoupa);
        tvDetalhesRoupa.setText("Cor/Tamanho: " + pedido.getCor() + " / " + pedido.getTamanho());

        TextView tvQuantidade = listItemView.findViewById(R.id.tvQuantidade);
        tvQuantidade.setText("Qtd: " + pedido.getQuantidade());

        TextView tvNomeCliente = listItemView.findViewById(R.id.tvNomeCliente);
        tvNomeCliente.setText("Cliente: " + pedido.getNomeCliente());

        TextView tvTelefone = listItemView.findViewById(R.id.tvTelefone);
        tvTelefone.setText("Telefone: " + pedido.getTelefone());

        TextView tvSituacaoPagamento = listItemView.findViewById(R.id.tvSituacaoPagamento);
        tvSituacaoPagamento.setText("Pagamento: " + pedido.getSituacaoPagamento());

        if ("Pago".equals(pedido.getSituacaoPagamento())) {
            tvSituacaoPagamento.setTextColor(context.getResources().getColor(R.color.status_positivo));
        } else {
            tvSituacaoPagamento.setTextColor(context.getResources().getColor(R.color.status_negativo));
        }

        return listItemView;
    }//getView
}
