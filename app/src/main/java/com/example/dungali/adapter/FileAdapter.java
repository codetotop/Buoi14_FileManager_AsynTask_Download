package com.example.dungali.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dungali.buoi14_filemanager_asyntask_download.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dung Ali on 6/24/2017.
 */

public class FileAdapter extends BaseAdapter {

    Activity context;
    ArrayList<File> arrFile;

    public FileAdapter(Activity context, ArrayList<File> arrFile) {
        this.context = context;
        this.arrFile = arrFile;
    }

    @Override
    public int getCount() {
        return arrFile.size();
    }

    @Override
    public Object getItem(int i) {
        return arrFile.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = this.context.getLayoutInflater();
            v = inflater.inflate(R.layout.item, viewGroup, false);
            viewHolder.imgFile = v.findViewById(R.id.imgFile);
            viewHolder.tvNameFile = v.findViewById(R.id.tvNameFile);
            viewHolder.tvPathFile = v.findViewById(R.id.tvpathFile);

            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        File file = arrFile.get(i);
        viewHolder.tvNameFile.setText(file.getName());
        viewHolder.tvPathFile.setText(file.getPath());
        if (file.isDirectory()) {
            viewHolder.imgFile.setImageResource(R.drawable.folder);
        } else {
            if (!file.isDirectory())
                viewHolder.imgFile.setImageResource(R.drawable.file);
        }
        return v;
    }

    class ViewHolder {
        ImageView imgFile;
        TextView tvNameFile;
        TextView tvPathFile;
    }
}
