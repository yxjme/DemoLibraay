package com.jewelermobile.gangfu.zdydemo1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class ContentFragment extends Fragment {

    private RecyclerView detail_recycler;

    public ContentFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_content,container,false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        getData();
        detail_recycler = view.findViewById(R.id.detail_recycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        detail_recycler.setLayoutManager(gridLayoutManager);
        detail_recycler.addItemDecoration(new MyItem2());
        detail_recycler.addItemDecoration(new ItemHeaderDecoration(getActivity(),list));
        detail_recycler.setAdapter(new MyAdapter());

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return list.get(i).isTitle() ? 3:1;
            }
        });
    }


    List<DetailBean> list = new ArrayList<>();
    private void getData(){
        for (int i=0;i<10;i++){
            list.add(new DetailBean(true,"标题"+i,"tag"+i));
            for(int j = 0 ; j < 20 ; j++){
                list.add(new DetailBean(false,"内容"+j,"tag"+i));
            }
        }
        Log.v("list=======","list="+new Gson().toJson(list));
    }


    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private static final int TITLE  = 1;
        private static final int NORMAL  = 2;

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return i==TITLE ? new MyAdapter.MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.detail_title, viewGroup, false))
                    :new MyAdapter.MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.item_layout, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int i) {
            holder.title_textview.setText(list.get(i).getName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public int getItemViewType(int position) {
            return list.get(position).isTitle() ? TITLE:NORMAL;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title_textview;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title_textview = itemView.findViewById(R.id.title_textview);
            }
        }
    }
}
