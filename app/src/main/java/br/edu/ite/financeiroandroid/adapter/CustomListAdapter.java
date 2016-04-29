package br.edu.ite.financeiroandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.edu.ite.financeiroandroid.adapter.dto.ItemAdapterDTO;

public abstract class CustomListAdapter extends BaseAdapter{

    private final List<ItemAdapterDTO> items;
    private final Context context;
    private final LayoutInflater inflater;

    public CustomListAdapter(Context context, List<ItemAdapterDTO> items) {
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
