package com.example.thelongdarkhandbook;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;

import android.os.PersistableBundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ContentMapActivity extends AppCompatActivity {

    private TextView text_name;
    private TextView text_content;
    private  int category=0;
    private  int position=0;
    private int [] array_map= {R.string.map1,R.string.map2,R.string.map3,R.string.map4};
    private int [] array_maptext= {R.string.map1text,R.string.map2text,R.string.map3text,R.string.map4text};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_map);
        text_name=findViewById(R.id.name_uslov);
        text_content=findViewById(R.id.text_content);





 }





private void reciveIntent(){
getI
    Intent i = getIntent();
    if(i != null){
        category=i.getIntExtra( "categore",0);
        position=i.getIntExtra( "position",0);
    }

}
}
