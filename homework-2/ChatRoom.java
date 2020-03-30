package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {
    private ImageView imageview;
    private TextView textview1,textview2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        imageview=findViewById(R.id.btn_send_info);
        textview1=findViewById(R.id.tv_content_info);
        textview2=findViewById(R.id.ed_say);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textview1.append(textview2.getText()+"\n");
                textview2.setText(null);
            }
        });
    }
}
