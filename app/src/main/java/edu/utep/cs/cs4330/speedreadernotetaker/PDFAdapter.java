package edu.utep.cs.cs4330.speedreadernotetaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.ArrayList;

public class PDFAdapter extends ArrayAdapter<File> {

    Context context;
    ViewHolder viewHolder;
    ArrayList<File> arrayList_pdf;

    public PDFAdapter(Context context, ArrayList<File> arrayList_pdf) {
        super(context, R.layout.adapter_pdf, arrayList_pdf);
        this.context = context;
        this.arrayList_pdf = arrayList_pdf;
    }

    @Override
    public int getItemViewType (int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        if(arrayList_pdf.size() > 0) {
            return arrayList_pdf.size();
        }
        else return 1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_pdf, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.filename = (TextView) convertView.findViewById(R.id.fileName);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.filename.setText(arrayList_pdf.get(position).getName());
        return convertView;
    }

    public class ViewHolder {
        TextView filename;
    }
}
