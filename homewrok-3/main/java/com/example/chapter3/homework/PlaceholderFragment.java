package com.example.chapter3.homework;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

@SuppressLint("ValidFragment")
public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animationview;
    private ListView mListview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationview = view.findViewById(R.id.ex3_animationview);
        mListview = view.findViewById(R.id.rv_list);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable(){
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                AlphaAnimation alphaAnimation1 = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation1.setDuration(1000);
                alphaAnimation1.setFillAfter(true);//动画结束时保持结束的动画
                AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
                alphaAnimation2.setDuration(1000);
                alphaAnimation2.setFillAfter(true);//动画结束时保持结束的动画

                animationview.setAnimation(alphaAnimation1);
                mListview.setAdapter(new ListBaseAdapter());
                mListview.setAnimation(alphaAnimation2);
            }
        }, 5000);
    }

    public static class ListBaseAdapter extends BaseAdapter {

        private static final int NUM_LIST_ITEMS = 10;


        @Override
        public int getCount() {
            return NUM_LIST_ITEMS;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(parent.getContext(), R.layout.item, null);
                holder.viewHolderIndex = convertView.findViewById(R.id.text_view);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.viewHolderIndex.setText(String.format("item %s", position+1));
            return convertView;
        }

        private static class ViewHolder {
            private TextView viewHolderIndex;
        }
    }
}
