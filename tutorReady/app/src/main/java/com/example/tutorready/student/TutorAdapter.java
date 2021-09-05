package com.example.tutorready.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tutorready.R;

import java.util.List;

public class TutorAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private List<Tutor> listTutor;

    public TutorAdapter(Context context, int layout, List<Tutor> listTutor) {
        this.context = context;
        this.layout = layout;
        this.listTutor = listTutor;
    }

    @Override
    public int getCount() {
        return listTutor.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtFullname, txtPhone,txtAddress;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txtFullname = (TextView) view.findViewById(R.id.tv_fullname);
            holder.txtPhone = (TextView) view.findViewById(R.id.tv_phone);
            holder.txtAddress = (TextView) view.findViewById(R.id.tv_address);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Tutor tutor = listTutor.get(position);
        holder.txtFullname.setText(tutor.getFullname());
        holder.txtPhone.setText("số điện thoại "+ tutor.getPhone());
        holder.txtAddress.setText("Địa chỉ "+tutor.getAddress());

        return null;
    }
}
