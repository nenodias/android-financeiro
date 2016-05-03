package br.edu.ite.financeiroandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;

public class LancamentoListAdapter extends CustomListAdapter {

    public LancamentoListAdapter(Context context, List<ItemAdapterDTO> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LancamentoListSupport itemSupport = null;
        convertView = inflater.inflate(R.layout.layout_lancamento, null);
        itemSupport = new LancamentoListSupport(convertView);
        convertView.setTag(itemSupport);
        itemSupport.setValues( items.get(position) );
        return convertView;
    }
}
