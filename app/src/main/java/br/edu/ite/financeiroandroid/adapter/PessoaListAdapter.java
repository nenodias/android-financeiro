package br.edu.ite.financeiroandroid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.edu.ite.financeiroandroid.R;
import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;

public class PessoaListAdapter extends CustomListAdapter {

    public PessoaListAdapter(Context context, List<ItemAdapterDTO> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PessoaListSupport itemSupport = null;
        if(convertView != null){
            convertView = inflater.inflate(R.layout.layout_pessoas, null);
            itemSupport = new PessoaListSupport(convertView);
            convertView.setTag(itemSupport);
            itemSupport.setValues( items.get(position) );
        }
        return convertView;
    }
}
