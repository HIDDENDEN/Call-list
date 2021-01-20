package com.example.module_2_week_2_recicler_view;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.concurrent.TimeUnit;

public class BackgroundContentProvider extends AsyncTaskLoader<String> {

    String id = "";

    public BackgroundContentProvider(@NonNull Context context) {
        super(context);
    }

    public void SetId(String id){
        this.id = id;
    }

    @Nullable
    @Override
    public String loadInBackground() {

        //sleep for five seconds
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //query возвращает cursor
        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},//какой столбец (в данном случае номер телефона)
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " + ContactsContract.CommonDataKinds.Phone.TYPE + " = ?",//нам нужен номер того человека, на которого мы щелкнули. Вместо знака ? подставятся значения из след аргумента
                new String[]{id, String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)},
                null
        );

        if (cursor != null && cursor.moveToFirst()) {//moveToFirst() переносит курсор к первому элементу таблицы
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            cursor.close();

            return number;
            //запуск приложения для звонков
//            startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + number)));
        }
        return "";

    }
}
