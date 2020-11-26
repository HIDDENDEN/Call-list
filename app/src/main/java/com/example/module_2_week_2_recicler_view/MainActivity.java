package com.example.module_2_week_2_recicler_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //т.к. при перевороте экрана state фрагмента сохраняется,
        // то добавляем фрагмент только при первом создании экрана, иначе фрагмент сам добавится
        
        if (savedInstanceState == null) {  //значит это первое создание экрана

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RecyclerFragment.newInstance()) //R.id.container - куда надуваем
                    .commit();
        }
    }
}