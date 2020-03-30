package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import chapter.android.aweme.ss.com.homework.model.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.PullParser;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        mRecyclerView = findViewById(R.id.rv_list);//获取RecyclerView控件
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Exercises3.this));
        int position=-1,count=0;
        try {
            InputStream assetInput = getAssets().open("data.xml");
            List<Message> messages = PullParser.pull2xml(assetInput);
            for (Message message : messages) {
                count++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        int[]image1=new int[count];
        int[]image2=new int[count];
        String[] list1=new String[count];
        String[] list2=new String[count];
        String[] list3=new String[count];
        try {
              InputStream assetInput = getAssets().open("data.xml");
              List<Message> messages = PullParser.pull2xml(assetInput);
              for (Message message : messages) {
                  position++;
                  if(message.isOfficial()==true)
                      image2[position]=R.drawable.im_icon_notice_official;
                  if(message.getIcon().equals("TYPE_ROBOT")){
                      image1[position]=R.drawable.session_robot;
                      Log.d("exercise3",message.getIcon()+image1[position]+'\n');
                  }
                  else if(message.getIcon().equals("TYPE_GAME")){
                      image1[position]=R.drawable.icon_micro_game_comment;
                      Log.d("exercise3",message.getIcon()+image1[position]+'\n');
                  }
                  else if(message.getIcon().equals("TYPE_SYSTEM")){
                      image1[position]=R.drawable.session_system_notice;
                      Log.d("exercise3",message.getIcon()+image1[position]+'\n');
                  }
                  else if(message.getIcon().equals("TYPE_STRANGER")){
                      image1[position]=R.drawable.session_stranger;
                      Log.d("exercise3",message.getIcon()+image1[position]+'\n');
                  }
                  else{
                      image1[position]=R.drawable.icon_girl;
                      Log.d("exercise3",message.getIcon()+image1[position]+'\n');
                  }
                  list1[position]=message.getDescription();
                  list2[position]=message.getTime();
                  list3[position]=message.getTitle();
                  //Log.d("exercise3",message.getIcon()+'\n');
             }
        } catch (Exception exception) {
             exception.printStackTrace();
          }
        mRecyclerView.setAdapter(new LinearAdapter(Exercises3.this,list1,list2,list3,image1,image2,count));
    }

}
