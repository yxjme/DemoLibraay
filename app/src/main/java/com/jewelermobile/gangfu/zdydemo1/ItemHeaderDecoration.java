package com.jewelermobile.gangfu.zdydemo1;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by User on 2017/7/24.
 */

public class ItemHeaderDecoration extends RecyclerView.ItemDecoration {


    private Context mContext;
    private List<DetailBean> mList;
    public static String currentTag = "0";
    private LayoutInflater mLayoutInflater;
    private int mTitleHight = 50;


    public ItemHeaderDecoration(Context context , List<DetailBean> dataList) {
        super();
        this.mContext = context;
        this.mList = dataList;
        mTitleHight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics());
        // mTitleTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, context.getResources().getDisplayMetrics());
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }



    public static void setCurrentTag(String tag){
        ItemHeaderDecoration.currentTag = tag;
    }



    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }



    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //获取到视图中第一个可见的item的position
        GridLayoutManager manager = (GridLayoutManager) parent.getLayoutManager();
        int position = manager.findFirstVisibleItemPosition();
        String tag = mList.get(position).getTag();
        View child = parent.findViewHolderForLayoutPosition(position).itemView;
        int nextPosition = position+2;
        boolean flag = false ;
        if(nextPosition < mList.size()){
            String suspensionTag = mList.get(nextPosition).getTag();
            Log.v("====child=======","=="+child.getTop());
            if(!TextUtils.isEmpty(tag) && !tag.equals(suspensionTag)){
                if(child.getHeight()+child.getTop() < mTitleHight){
                    c.save();
                    flag = true ;
                    c.translate(0,child.getHeight()+child.getTop()-mTitleHight);
                }
            }
        }


        View topTitleView = mLayoutInflater.inflate(R.layout.detail_title,parent,false);
        TextView textView =  topTitleView.findViewById(R.id.title_textview);
        textView.setText("分类"+tag);
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) topTitleView.getLayoutParams();
        if (lp != null) {
            //依次调用 measure,layout,draw方法，将复杂头部显示在屏幕上。
            topTitleView.measure(mWidth(lp,parent), mHeight(lp,parent));
            //topTitleView在屏幕中显示的位置
            topTitleViewLayout(parent,topTitleView);
            //Canvas默认在视图顶部，无需平移，直接绘制
            topTitleView.draw(c);
            if (flag){
                c.restore();//还原画布
            }
        }
    }


    /**
     * 显示的位置
     *
     * @param parent
     * @param topTitleView
     */
    private void topTitleViewLayout(RecyclerView parent, View topTitleView) {
        int l = parent.getPaddingLeft();//如果父布局有内句左边
        int t = parent.getTop();//当前距离父布局顶部的距离
        int r = parent.getPaddingLeft() + topTitleView.getMeasuredWidth();;
        int b = t +topTitleView.getMeasuredHeight();
        topTitleView.layout(l,t,r,b);
    }




    /**
     * 高度
     *
     * @param lp
     * @param parent
     * @return
     */
    private int mHeight(RecyclerView.LayoutParams lp , RecyclerView parent) {
        int h;
        //高度同理
        if (lp.height == ViewGroup.LayoutParams.MATCH_PARENT) {
            h = View.MeasureSpec.makeMeasureSpec(
                    parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.EXACTLY);
        } else if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            h = View.MeasureSpec.makeMeasureSpec(
                    parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom(), View.MeasureSpec.AT_MOST);
        } else {
            h = View.MeasureSpec.makeMeasureSpec(mTitleHight, View.MeasureSpec.EXACTLY);
        }
        return h;
    }



    /**
     * 宽
     *
     * @param lp
     * @param parent
     * @return
     */
    private int mWidth(RecyclerView.LayoutParams lp, RecyclerView parent) {
        int w;
        if (lp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
            //如果是MATCH_PARENT，则用父控件能分配的最大宽度和EXACTLY构建MeasureSpec。
            w = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.EXACTLY);
        } else if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            //如果是WRAP_CONTENT，则用父控件能分配的最大宽度和AT_MOST构建MeasureSpec。
            w = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.AT_MOST);
        } else {
            //否则则是具体的宽度数值，则用这个宽度和EXACTLY构建MeasureSpec。
            w = View.MeasureSpec.makeMeasureSpec(lp.width, View.MeasureSpec.EXACTLY);
        }
        return w;
    }


    /**
     * @param val
     * @return
     */
    public int dpToPx(int val){
        float density = mContext.getResources().getDisplayMetrics().density;
        float px =  val * density + 0.5f;
        return (int) px;
    }


}