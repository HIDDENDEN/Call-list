package com.example.module_2_week_2_recicler_view;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_2_week_2_recicler_view.Mock.Mock;
import com.example.module_2_week_2_recicler_view.Mock.MockHolder;

//будет выдергивать данные из таблицы Cursor
public class ContactsAdapter extends RecyclerView.Adapter<MockHolder> {

    private Cursor mCursor;

    @NonNull
    @Override
    public MockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //в parent хранится контекст

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.li_mock, parent, false);
        return new MockHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MockHolder holder, int position) {
        //нужно получить данные из курсора и сормить холдеру, чтобы он их показал
        if (mCursor.moveToPosition(position)) {//возвращает true , если курсор успешно переместился на эту позицию

            String name = mCursor.getString(
                    mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)//индекс колонки из которой нужно выдернуть данные
            );

            int id = mCursor.getInt(
                    mCursor.getColumnIndex(ContactsContract.Contacts._ID)
            );

            holder.bind(new Mock(name,id));
        }
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    //добавление Cursor в адаптер
    public void swapCursor(Cursor cursor) {
        if (cursor != null && cursor != mCursor) {
            if (mCursor != null) mCursor.close();
            mCursor = cursor;
            notifyDataSetChanged();
            // поменяли 2 курсора местами
        }
    }
}
