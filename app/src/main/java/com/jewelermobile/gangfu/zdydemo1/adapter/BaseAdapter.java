package com.jewelermobile.gangfu.zdydemo1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {

    private final int headType = -1;
    private final int footerType = -2;
    private final int commonType = 0 ;
    private View headView;
    private View footerView;
    private List<T> list = new ArrayList<>();
    private Context context;


    /**
     * @param list
     */
    public BaseAdapter(Context context,List<T> list){
        if (list!=null){
            this.list = list;
        }
        this.context = context;
    }


    /**
     * 添加头部的view
     *
     * @param view
     */
    public BaseAdapter addHeadView(View view){
        this.headView =view ;
        return this;
    }


    /**
     * 添加底部view
     *
     * @param view
     */
    public BaseAdapter addFooterView(View view){
        this.footerView =view ;
        return this;
    }


    /**
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (isHeadView(position))
            return headType ;
        if (isFooterView(position))
            return footerType ;
        return commonType;
    }


    /**
     * 判断是 head
     * @param position
     * @return
     */
    public boolean isHeadView(int position ){
        if (headView != null && position == 0 ){
            return true ;
        }
        return false ;
    }


    /**
     *
     * @param position
     * @return
     */
    public boolean isFooterView(int position){
        if (footerView != null){
            if (headView!=null ? (position==list.size()+1):(position==list.size())){
                return true ;
            }
        }
        return false ;
    }



    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CommonViewHolder holder = null;
        switch (i){
            case headType:
                holder = new CommonViewHolder(headView);
                break;
            case footerType:
                holder = new CommonViewHolder(footerView);
                break;
            case commonType:
                holder = new CommonViewHolder(LayoutInflater.from(context).inflate(getLayoutId(),viewGroup,false));
                break;
        }
        return holder ;
    }


    @Override
    public void onBindViewHolder(@NonNull CommonViewHolder viewHolder, int position) {
        if (isHeadView(position)){
            headView(viewHolder);
        } else if (isFooterView(position)){
            footerView(viewHolder);
        }else {
            final int p = headView != null ? position - 1 : position;
            itemView(viewHolder,list.get(p),p);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!=null);
                    onItemClickListener.onItemClick(v,list.get(p));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        int count = 0;
        if (headView!=null){
            count += 1;
        }
        if (footerView != null){
            count += 1;
        }
        return list == null ? count : list.size()+count;
    }


    /**
     * 列表 item
     * @return
     */
    public abstract int getLayoutId();


    /**
     * @param viewHolder
     * @param position
     * @param t
     */
    public abstract void itemView(CommonViewHolder viewHolder,T t ,int position);


    /**
     * @param viewHolder
     */
    public void headView(CommonViewHolder viewHolder){

    }

    /**
     * @param viewHolder
     */
    public void footerView( CommonViewHolder viewHolder){

    }


    @Override
    public void onViewAttachedToWindow(@NonNull CommonViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams && (isFooterView(holder.getLayoutPosition())||isHeadView(holder.getLayoutPosition()))) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }


    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener<T>{
        void onItemClick(View v, T t);
    }
}
