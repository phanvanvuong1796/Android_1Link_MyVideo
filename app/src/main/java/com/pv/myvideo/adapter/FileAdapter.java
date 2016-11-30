package com.pv.myvideo.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pv.myvideo.BR;
import com.pv.myvideo.R;
import com.pv.myvideo.model.FileModel;

import java.util.List;

/**
 * Created by phanvuong on 11/28/16.
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder>{

    private List<FileModel> modelList;

    public FileAdapter(List<FileModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item, parent, false);

        return new FileViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        ViewDataBinding binding = holder.getViewDataBinding();
        binding.setVariable(BR.fileItem, modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder{

        private ViewDataBinding mViewDataBinding;

        public FileViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }
}
