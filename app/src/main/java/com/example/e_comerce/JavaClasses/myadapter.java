package com.example.e_comerce.JavaClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_comerce.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class myadapter extends BaseAdapter {
    Context mycontext;
    LayoutInflater inflater;
    List<mylist> L;
    ArrayList<mylist> ArrL;

    public myadapter(Context context, ArrayList<mylist> l) {
        mycontext = context;
        L = l;
        inflater = LayoutInflater.from(mycontext);
        this.ArrL = new ArrayList<>();
        this.ArrL.addAll(L);
    }

    public class viewHolder {
        TextView T;
        ImageView img;
    }

    @Override
    public int getCount() {
        return L.size();
    }

    @Override
    public Object getItem(int i) {
        return L.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        viewHolder holder;

        if (view == null) {
            holder = new viewHolder();
            view = inflater.inflate(R.layout.list_view, null);

            holder.T = view.findViewById(R.id.title);
            holder.img = view.findViewById(R.id.icon);

            view.setTag(holder);

        } else {
            holder = (viewHolder) view.getTag();
        }

        holder.T.setText(L.get(position).getTitle());
        holder.img.setImageResource(L.get(position).getIcon());



        return view;
    }

    public void filter(String chartext) {
        chartext = chartext.toLowerCase(Locale.getDefault());
        L.clear();
        if (chartext.length() == 0) {
            L.addAll(ArrL);
        } else {
            for (mylist M : ArrL) {
                if (M.getTitle().toLowerCase(Locale.getDefault()).contains(chartext)) {
                    L.add(M);
                }
            }
        }
        notifyDataSetChanged();
    }
}
