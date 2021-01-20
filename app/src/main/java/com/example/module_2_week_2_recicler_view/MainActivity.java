package com.example.module_2_week_2_recicler_view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.Notification;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.OnItemClickListener, LoaderManager.LoaderCallbacks<String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
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

    @Override
    public void onItemClick(String id) {

//        //query возвращает cursor
//        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},//какой столбец (в данном случае номер телефона)
//                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " + ContactsContract.CommonDataKinds.Phone.TYPE + " = ?",//нам нужен номер того человека, на которого мы щелкнули. Вместо знака ? подставятся значения из след аргумента
//                new String[]{id, String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)},
//                null
//        );
//
//        if (cursor != null && cursor.moveToFirst()) {//moveToFirst() переносит курсор к первому элементу таблицы
//            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            cursor.close();
//
//            //запуск приложения для звонков
//            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + number)));
//        }
        if (LoaderManager.getInstance(this).hasRunningLoaders() == false) {
            Bundle bundle = new Bundle();
            bundle.putString("id",id.toString());
            System.out.println("putId = "+ id);
            LoaderManager.getInstance(this).restartLoader(LOADER_IDENTIFICATOR, bundle, this).forceLoad();
        }

        // !!! forceLoad() call is essential to use after initLoader()

    }

    public static final int LOADER_IDENTIFICATOR = 21;
//    private BackgroundContentProvider mBackgroundContentProvider;
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        String numId = args.getString("id");
        System.out.println("getId = "+ numId);
        BackgroundContentProvider mBackgroundContentProvider = new BackgroundContentProvider(this);
        mBackgroundContentProvider.SetId(numId);

//        mBackgroundContentProvider = new BackgroundContentProvider(this);
//        return mBackgroundContentProvider;
        return mBackgroundContentProvider;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        if (data !=""){
            String number = data;
            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + number)));
        }else{
            Toast.makeText(this,"No number",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}