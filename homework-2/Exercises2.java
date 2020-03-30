package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup;
import android.util.Log;
import chapter.android.aweme.ss.com.homework.R;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    private TextView exercise2Display;
    private RelativeLayout exercise2List;
    private static final String TAG = "exercise2";

    public int getAllChildViewCount(ViewGroup view) {
        //todo 补全你的代码
        int count =view.getChildCount();
        int totalCount =count;
        for(int i = 0; i<count ;i++){
            View child =view.getChildAt(i);
            if(child != null && child instanceof  ViewGroup ){
                totalCount += ((ViewGroup) child).getChildCount();
            }
        }
        Log.d(TAG," TotalCount= " + totalCount);
        return totalCount;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im_list_item);
        exercise2Display=findViewById(R.id.exercise2);
        exercise2List=findViewById(R.id.list);
        exercise2Display.setText("viewcount="+getAllChildViewCount(exercise2List));
    }
}
