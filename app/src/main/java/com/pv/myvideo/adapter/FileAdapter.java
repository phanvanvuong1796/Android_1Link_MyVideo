package com.pv.myvideo.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pv.myvideo.R;
import com.pv.myvideo.databinding.ListItemBinding;
import com.pv.myvideo.model.FileModel;

import java.util.List;

/**
 * Created by phanvuong on 11/28/16.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder>{

    public interface CallBack{
        public void onClick(FileModel fileModel);
    }

    private CallBack callBack;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private List<FileModel> modelList;

    public FileAdapter(List<FileModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        FileModel fileModel = modelList.get(position);
        holder.bind(fileModel);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder{

        private ListItemBinding mListItemBinding;

        public FileViewHolder(View view) {
            super(view);
            mListItemBinding = DataBindingUtil.bind(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClick(modelList.get(getPosition()));
                }
            });
        }
        public void bind(FileModel fileModel){
            mListItemBinding.setFileItem(fileModel);
        }
    }


}
