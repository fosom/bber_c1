package com.bber.company.android.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bber.company.android.BR;
import com.bber.company.android.R;
import com.bber.company.android.viewmodel.BaseViewModel;
import com.bber.company.android.widget.RecyclerItemClickListener;

import java.util.List;


/**
 * 项目名称：
 * 类描述：
 * 创建人：PHC
 * 创建时间：2016/1/18 12:17
 * 修改人：PHC
 * 修改时间：2016/1/18 12:17
 * 修改备注：
 *
 * @version v1.0
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private Context mContext;
    private List dataSources;
    private int itemLayout;
    private BaseViewModel viewModel;
    private OnItemClickListener mListener;
    public RecycleAdapter(Context _context, List _data, int _itemlayout) {
        mContext = _context;
        dataSources = _data;
        itemLayout = _itemlayout;
    }

    public RecycleAdapter(Context _context, List _data, int _itemlayout, BaseViewModel viewModel) {
        mContext = _context;
        dataSources = _data;
        itemLayout = _itemlayout;
        this.viewModel = viewModel;
    }

    // 点击回调
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener){
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(itemLayout, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {

        if (dataSources.size() > 0) {
            if (i >= dataSources.size()) {
                i = dataSources.size() - 1;
            }
            viewHolder.bind(dataSources.get(i), i);
        }
        viewHolder.text_phone.setText((String) dataSources.get(i));
        final int finalI = i;
        viewHolder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(viewHolder.binding.getRoot(),
                        finalI);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            viewHolder.binding.getRoot().setBackground(mContext.getDrawable(R.drawable.poplu_window1_stroke));
        }

    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }
    @Override
    public int getItemCount() {
        if (dataSources==null){
            return 0;
        }
        return dataSources.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        public TextView text_phone;
        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            text_phone = (TextView) binding.getRoot().findViewById(R.id.text_phone1);
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }

        public void bind(@NonNull Object data, int _index) {
            binding.setVariable(BR.item, data);
            try {
                binding.setVariable(BR.index, _index);
                if (viewModel != null) {
                    binding.setVariable(BR.viewModel, viewModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //binding.executePendingBindings();
        }
    }
}
