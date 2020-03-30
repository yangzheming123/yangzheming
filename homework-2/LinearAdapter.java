package chapter.android.aweme.ss.com.homework;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import chapter.android.aweme.ss.com.homework.widget.CircleImageView;


public class LinearAdapter extends RecyclerView.Adapter <LinearAdapter.LinearViewHolder> {
    @NonNull
    private Context mcontext;
    private String[] list1,list2,list3;
    private int[] image1;
    private int[] image2;
    private int count;
    private static final String TAG = "exercise3";

    public LinearAdapter(Context context, String[] list1, String[] list2, String[] list3, int[] image1, int[] image2, int count){
        this.mcontext=context;
        this.list1=list1;
        this.list2=list2;
        this.list3=list3;
        this.image1=image1;
        this.image2=image2;
        this.count=count;
    }//构造函数
    @Override
    public LinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new LinearViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.im_list_item,viewGroup,false));//传入一个布局，表示每个Item长什么样子
    }//创建ViewHolder

    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.LinearViewHolder viewHolder, int i) {
            viewHolder.textview1.setText(list1[i]);
            viewHolder.textview2.setText(list2[i]);
            viewHolder.textview3.setText(list3[i]);
            viewHolder.imageview1.setImageResource(image1[i]);
            viewHolder.imageview2.setImageResource(image2[i]);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(mcontext,ChatRoom.class);
                    mcontext.startActivity(intent);
                }
            });
                //Log.d(TAG,i+"\n");
    }//绑定ViewHolder


    @Override
    public int getItemCount() {
        return count;
    }//列表长度

    class LinearViewHolder extends RecyclerView.ViewHolder{
        //在此声明布局里的控件
        private TextView textview1,textview2,textview3;
        private CircleImageView imageview1;
        private ImageView imageview2;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            textview1=itemView.findViewById(R.id.tv_description);
            textview2=itemView.findViewById(R.id.tv_time);
            textview3=itemView.findViewById(R.id.tv_title);
            imageview1=itemView.findViewById(R.id.iv_avatar);
            imageview2=itemView.findViewById(R.id.robot_notice);
        }
    }
}