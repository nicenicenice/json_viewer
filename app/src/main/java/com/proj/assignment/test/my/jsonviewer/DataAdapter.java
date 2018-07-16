package com.proj.assignment.test.my.jsonviewer;

import android.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<String> itemNameList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(final View layout) {

            super(layout);
            mTextView = itemView.findViewById(R.id.text_list_item);
            /*mTextView.setOnLongClickListener(new View.OnLongClickListener() {

                //dataMap.put(nameOfItem, jsonItem.toString());

                @Override public boolean onLongClick(View v) {
                    //String detailItemData = Utils.getStringWithAllJsonData(jsonItem);
                    String detailItemData = "zazaz";
                    new AlertDialog.Builder(layout.getContext())
                            .setMessage(detailItemData)
                            .show();
                    return true;
                }
            });*/
        }
    }

    public DataAdapter(List<String> itemNameList) {
        this.itemNameList = itemNameList;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_view, parent, false);

        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(itemNameList.get(position));
    }

    @Override
    public int getItemCount() {
        if (itemNameList == null)
            return 0;
        return itemNameList.size();
    }
}