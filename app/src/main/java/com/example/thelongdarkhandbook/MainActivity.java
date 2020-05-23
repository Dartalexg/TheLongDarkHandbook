package com.example.thelongdarkhandbook;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;

import android.text.Layout;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //-------------------------------------- Переменые меню

    private Switch Grom;
    private DrawerLayout drawer;//боковое меню
    private Toolbar toolbar;
    private int category_index;//следит за выбором пункта меню по стоку 0
    private int positionshow;//Следит выбрана ли глава в меню  истории
    //-------------------------------------- Переменые ипользуемых элементов
    private MediaPlayer player,playerstory1,playerstory2,playerstory3;
    private ListView list,listview;
    private LinearLayout map,animal,person,episode,set;
    private ImageView map_min_img,map_img,animal_img,person_img,episode_img;
    private TextView text_name_map,text_content_map,name_animal,opis_animal,name_person,opis_person,name_episode,cont_episode;
    private ScrollView scrollmap,scrollanimal,scroll_person,scrollepisode;
    PhotoViewAttacher mAttacher;
    //-------------------------------------- Переменые заполняемых списков
    private String[] array;
    private ArrayAdapter<String> adapter;
    //--------------------------------------------------------------------------- Массивы
    //-------------------------------------- Карты
    private int [] array_map= {R.string.map1,R.string.map2,R.string.map3,R.string.map4,R.string.map5,R.string.map6,R.string.map7,R.string.map8,R.string.map9,R.string.map10,R.string.map11,R.string.map12};
    private int [] array_maptext= {R.string.map1text,R.string.map2text,R.string.map3text,R.string.map4text,R.string.map5text,R.string.map6text,R.string.map7text,R.string.map8text,R.string.map9text,R.string.map10text,R.string.map11text,R.string.map12text};
    private int [] array_map_img= {R.drawable.mysterylake,R.drawable.raven_falls,R.drawable.coastal_highway,R.drawable.old_island,R.drawable.area_desolation,R.drawable.carter_dam,R.drawable.joyful_valley,R.drawable.timberwolf_mountain,R.drawable.forlorn_muskegg,R.drawable.broken_railroad,R.drawable.mountain_town,R.drawable.hushed_rive};
    private int [] array_map_min_img= {R.drawable.mysterylake_min,R.drawable.raven_falls_min,R.drawable.coastal_highway_min,R.drawable.old_island_min,R.drawable.area_desolation_min,R.drawable.carter_dam_min,R.drawable.joyful_valley_min,R.drawable.timberwolf_mountain_min,R.drawable.forlorn_muskeg_min,R.drawable.broken_railroad_min,R.drawable.mountain_town_min,R.drawable.hushed_rive_min};
    //-------------------------------------- Животные
    private int[] array_name_animal={R.string.animal1,R.string.animal2,R.string.animal3,R.string.animal4,R.string.animal5,R.string.animal6,R.string.animal7,R.string.animal8};
    private int[] array_img_animal={R.drawable.volf,R.drawable.elk,R.drawable.bear,R.drawable.rabbit,R.drawable.timberwolf,R.drawable.crow,R.drawable.deer,R.drawable.coho};
    private int[] array_opis_animal={R.string.animal1text,R.string.animal2text,R.string.animal3text,R.string.animal4text,R.string.animal5text,R.string.animal6text,R.string.animal7text,R.string.animal8text};
    //-------------------------------------- Персонажи
    private int[] array_name_person={R.string.person1,R.string.person2,R.string.person3,R.string.person4,R.string.person5,R.string.person6,R.string.person7,R.string.person8};
    private int[] array_img_person={R.drawable.will_mackenzie,R.drawable.hobbs,R.drawable.greymother,R.drawable.astrid_greenwood,R.drawable.mathis,R.drawable.methuselah,R.drawable.molly,R.drawable.jeremy};
    private int[] array_opis_person={R.string.person1text,R.string.person2text,R.string.person3text,R.string.person4text,R.string.person5text,R.string.person6text,R.string.person7text,R.string.person8text};
    //---------------------------------------------------- История
    //-------------------------------------- Эпизод 1
    private int[] array_name_episode1={R.string.name_episode11,R.string.name_episode12,R.string.name_episode13,R.string.name_episode14};
    private int[] array_img_episode1={R.drawable.episode11,R.drawable.episode13,R.drawable.episode12,R.drawable.episode14};
    private int[] array_cont_episode1={R.string.cont_episode11,R.string.cont_episode12,R.string.cont_episode13,R.string.cont_episode14};
    //-------------------------------------- Эпизод 2
    private int[] array_name_episode2={R.string.name_episode21,R.string.name_episode22,R.string.name_episode23,R.string.name_episode24,R.string.name_episode25};
    private int[] array_img_episode2={R.drawable.episode21,R.drawable.episode22,R.drawable.episode23,R.drawable.episode24,R.drawable.episode25};
    private int[] array_cont_episode2={R.string.cont_episode21,R.string.cont_episode22,R.string.cont_episode23,R.string.cont_episode24,R.string.cont_episode25};
    //-------------------------------------- Эпизод 3
    private int[] array_name_episode3={R.string.name_episode31,R.string.name_episode32,R.string.name_episode33,R.string.name_episode34};
    private int[] array_img_episode3={R.drawable.episode31,R.drawable.episode32,R.drawable.episode33,R.drawable.episode34};
    private int[] array_cont_episode3={R.string.cont_episode31,R.string.cont_episode32,R.string.cont_episode33,R.string.cont_episode34};
    //---------------------------------------------------- История
    //-------------------------------------- Музыка
    private int[] arrayplay={R.raw.music,R.raw.episod1,R.raw.episod2,R.raw.episod3};
    //--------------------------------------------------------------------------- Массивы
private ImageView t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //--------------------------------------------------------Cкрытие верхней шторки
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //--------------------------------------------------------
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);//
        setSupportActionBar(toolbar);
        list = findViewById(R.id.listview);//Подкоючение к списку ниже заполнение
        array=getResources().getStringArray(R.array.story_array);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,new ArrayList<String>(Arrays.asList(array)));
        list.setAdapter(adapter);



















        //--------------------------------------------------------------------------------------- Музыка
        player=MediaPlayer.create(this,arrayplay[0]);//Плэир
        playerstory1=MediaPlayer.create(this,arrayplay[1]);
        playerstory2=MediaPlayer.create(this,arrayplay[2]);
        playerstory3=MediaPlayer.create(this,arrayplay[3]);
        player.setLooping(true);
        player.seekTo(0);
        player.start();
        playerstory1.start();
        playerstory2.start();
        playerstory3.start();
        playerstory1.pause();
        playerstory2.pause();
        playerstory3.pause();
       //--------------------------------------------------------------------------------------- Создание бокового меню
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        // toggle.syncState(); //показывает кнопку меню
        navigationView.setNavigationItemSelectedListener(this);
       //--------------------------------------------------------------------------------------- Слушатель нажатия элемента списков

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // Intent intent = new Intent(MainActivity.this,ContentMapActivity.class);
//intent.putExtra("categore",category_index);
//intent.putExtra("position",position);
// startActivity(intent);
                map = findViewById(R.id.map);
                animal=findViewById(R.id.animal);
                person=findViewById(R.id.person);
                episode=findViewById(R.id.episode);
                set=findViewById(R.id.set);

                text_name_map=findViewById(R.id.name_uslov);
                text_content_map=findViewById(R.id.text_content);
                name_animal=findViewById(R.id.name_animal);
                opis_animal=findViewById(R.id.opis_animal);
                name_person=findViewById(R.id.name_person);
                opis_person=findViewById(R.id.opis_person);
                name_episode=findViewById(R.id.name_episode);
                cont_episode=findViewById(R.id.cont_episode);

                map_min_img=findViewById(R.id.map_min_img);
                map_img=findViewById(R.id.map_img);
                animal_img=findViewById(R.id.animal_img);
                person_img=findViewById(R.id.img_person);
                episode_img=findViewById(R.id.img_episode);

                scrollmap=findViewById(R.id.scrollmap);
                scrollanimal=findViewById(R.id.scrollanimal);
                scroll_person=findViewById(R.id.scrollperson);
                scrollepisode=findViewById(R.id.scrollepisode);

                switch (category_index){
                    case 0:// История
                      switch (position){
                          case 0:// Эпизод 1
                              array=getResources().getStringArray(R.array.episode1_array);
                              adapter.clear();
                              adapter.addAll(array);
                              adapter.notifyDataSetChanged();
                              toolbar.setTitle(R.string.episode1);
                              category_index=6;
                              positionshow=0;

                              player.pause();
                              playerstory1.setLooping(true);
                              playerstory1.seekTo(0);
                              playerstory1.start();
                              break;

                          case 1:// Эпизод 2
                              array=getResources().getStringArray(R.array.episode2_array);
                              adapter.clear();
                              adapter.addAll(array);
                              adapter.notifyDataSetChanged();
                              toolbar.setTitle(R.string.episode2);
                              category_index=7;
                              positionshow=0;

                              player.pause();
                              playerstory2.setLooping(true);
                              playerstory2.seekTo(0);
                              playerstory2.start();
                              break;

                          case 2:// Эпизод 3
                              array=getResources().getStringArray(R.array.episode3_array);
                              adapter.clear();
                              adapter.addAll(array);
                              adapter.notifyDataSetChanged();
                              toolbar.setTitle(R.string.episode3);
                              category_index=8;
                              positionshow=0;

                              player.pause();
                              playerstory3.setLooping(true);
                              playerstory3.seekTo(0);
                              playerstory3.start();
                              break; }
                        break;

                    case 1://Карты
                        list.setVisibility(View.INVISIBLE);
                        map.setVisibility(View.VISIBLE);
                        scrollmap.scrollTo(0,0);
                        text_name_map.setText(array_map[position]);
                        text_content_map.setText(array_maptext[position]);
                        map_img.setImageResource(array_map_img[position]);
                        map_min_img.setImageResource(array_map_min_img[position]);
                        mAttacher = new PhotoViewAttacher(animal_img);
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        break;

                    case 2:

                        break;

                    case 3:
                        list.setVisibility(View.INVISIBLE);
                        person.setVisibility(View.VISIBLE);
                        scroll_person.scrollTo(0,0);
                        name_person.setText(array_name_person[position]);
                        opis_person.setText(array_opis_person[position]);
                        person_img.setImageResource(array_img_person[position]);

                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        break;

                    case 4:
                        list.setVisibility(View.INVISIBLE);
                        animal.setVisibility(View.VISIBLE);
                        scrollanimal.scrollTo(0,0);
                        name_animal.setText(array_name_animal[position]);
                        opis_animal.setText(array_opis_animal[position]);
                        animal_img.setImageResource(array_img_animal[position]);
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        break;
                    case 6:
                        list.setVisibility(View.INVISIBLE);
                        episode.setVisibility(View.VISIBLE);
                        scrollepisode.scrollTo(0,0);
                        name_episode.setText(array_name_episode1[position]);
                        cont_episode.setText(array_cont_episode1[position]);
                        episode_img.setImageResource(array_img_episode1[position]);
                        category_index=5;
                        positionshow=1;
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//блокирует вызов бокового меню
                        break;
                    case 7:
                        list.setVisibility(View.INVISIBLE);
                        episode.setVisibility(View.VISIBLE);
                        scrollepisode.scrollTo(0,0);
                        name_episode.setText(array_name_episode2[position]);
                        cont_episode.setText(array_cont_episode2[position]);
                        episode_img.setImageResource(array_img_episode2[position]);
                        category_index=55;
                        positionshow=1;
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//блокирует вызов бокового меню
                        break;
                    case 8:
                        list.setVisibility(View.INVISIBLE);
                        episode.setVisibility(View.VISIBLE);
                        scrollepisode.scrollTo(0,0);
                        name_episode.setText(array_name_episode3[position]);
                        cont_episode.setText(array_cont_episode3[position]);
                        episode_img.setImageResource(array_img_episode3[position]);
                        category_index=555;
                        positionshow=1;
                        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//блокирует вызов бокового меню
                        break;
                } }});


//-------------------------------------------------------- Отключение громкости функция не привязаня
        Grom = (Switch)findViewById(R.id.Grom);
        Grom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)
                {
                    player.setVolume(0,0);
                }
                else {
                    player.setVolume(1,1);
                } }});
        //-------------------------------------------------------- слушатель нажатия на картинку приветствия
        final ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               img.setVisibility(View.INVISIBLE);
                listview=findViewById(R.id.listview);
                listview.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);
            }});
    }
    @Override// Нажатие на кнопку назад
    public void onBackPressed() {
        //super.onBackPressed();
        goHome();
    }

    private void goHome() {
        if (category_index==6 | category_index==7 | category_index==8 & positionshow==0){//Возвращение на главную
            list.setVisibility(View.VISIBLE);
            episode.setVisibility(View.INVISIBLE);
            array=getResources().getStringArray(R.array.story_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.story);
            category_index=0;
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

if (playerstory1.isPlaying()){//Плэиры
    playerstory1.pause();
    player.start();
}else{
if (playerstory2.isPlaying()){
    playerstory2.pause();
    player.start();
    }
else{
if (playerstory3.isPlaying()){
    playerstory3.pause();
    player.start();
}}}

        }else{
        if(category_index==5 & positionshow==1 )//Возвращение на главную историй с эпизода 1
        {
            list.setVisibility(View.VISIBLE);
            episode.setVisibility(View.INVISIBLE);
            array=getResources().getStringArray(R.array.episode1_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.episode1);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            category_index=6;
            positionshow=0;
        }else
            {
        if (category_index==55 & positionshow==1 )  {//Возвращение на главную историй с эпизода 2
            list.setVisibility(View.VISIBLE);
            episode.setVisibility(View.INVISIBLE);
            array=getResources().getStringArray(R.array.episode2_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.episode2);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            category_index=7;
            positionshow=0;
        }
        else {
            if (category_index==555 & positionshow==1 ){//Возвращение на главную историй с эпизода 3
                list.setVisibility(View.VISIBLE);
                episode.setVisibility(View.INVISIBLE);
                array=getResources().getStringArray(R.array.episode3_array);
                adapter.clear();
                adapter.addAll(array);
                adapter.notifyDataSetChanged();
                toolbar.setTitle(R.string.episode3);
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                category_index=8;
                positionshow=0;
            }else
                {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        list.setVisibility(View.VISIBLE);
        map.setVisibility(View.INVISIBLE);
        animal.setVisibility(View.INVISIBLE);
        person.setVisibility(View.INVISIBLE);
        episode.setVisibility(View.INVISIBLE);
        drawer.closeDrawer(GravityCompat.START);}}}}
  }//Возвращение домой

    @Override//Создание бокового меню
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.story);
        return true;
    }

    @Override// обработка выбора пункта меню
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id==R.id.nav_story){
            array=getResources().getStringArray(R.array.story_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.story);
            category_index=0;
            playerstory1.pause();
            playerstory2.pause();
            playerstory3.pause();
            player.start();

           }
        else if (id==R.id.nav_map){

            array=getResources().getStringArray(R.array.maps_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.map);
            category_index=1;
            playerstory1.pause();
            playerstory2.pause();
            playerstory3.pause();
            player.start();

        }
        //else if (id==R.id.nav_subject){
            //array=getResources().getStringArray(R.array.subject_array);
            //adapter.clear();
            //adapter.addAll(array);
            //adapter.notifyDataSetChanged();
            //toolbar.setTitle(R.string.subject);
            //category_index=2;
            //playerstory1.pause();
            //playerstory2.pause();
           // player.start();
       // }
        else if (id==R.id.nav_tip){
            array=getResources().getStringArray(R.array.tip_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.tip);
            category_index=4;
            playerstory1.pause();
            playerstory2.pause();
            playerstory3.pause();
            player.start();
        }
        else if (id==R.id.nav_person){
            array=getResources().getStringArray(R.array.person_array);
            adapter.clear();
            adapter.addAll(array);
            adapter.notifyDataSetChanged();
            toolbar.setTitle(R.string.person);
            category_index=3;
            playerstory1.pause();
            playerstory2.pause();
            playerstory3.pause();
            player.start();

        }
       // else if (id==R.id.nav_setting){
      //  }
        else if (id==R.id.nav_VK){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/thelongdark"));
            startActivity(browserIntent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {//Полное перекрытие прилож
        super.onStart();
        if(category_index==6 | category_index==5){
            playerstory1.start();
        }else{
        if(category_index==7 | category_index==55){
            playerstory2.start();
        }else{
        if (category_index==8 | category_index==555){
            playerstory3.start();
        }else{
        player.start();}}}
    }
    @Override
    protected void onResume() {//сообщение или оповещение
        super.onResume();
        if(category_index==6 | category_index==5){
            playerstory1.start();
        }else{
            if(category_index==7 | category_index==55){
                playerstory2.start();
            }else{
                if (category_index==8 | category_index==555){
                    playerstory3.start();
                }else{
                    player.start();}}}
        }

    @Override//Остановка звука при свертовании приложения
    protected void onPause() {
        super.onPause();
        if (playerstory1.isPlaying()){//Плэиры
            playerstory1.pause();

        }else{
            if (playerstory2.isPlaying()){
                playerstory2.pause();
            }
            else{
                if (playerstory3.isPlaying()){
                    playerstory3.pause();
                }
            else{
            player.pause();
            }}}
    }







}
