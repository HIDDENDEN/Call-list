package com.example.module_2_week_2_recicler_view.Mock;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_2_week_2_recicler_view.ContactsAdapter;
import com.example.module_2_week_2_recicler_view.R;

public class MockHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mValue;

    private String mId;

    //itemView будет ссылаться  на li_mock (list item mock)
    public MockHolder(@NonNull View itemView) {
        super(itemView);

        mName = itemView.findViewById(R.id.tv_name);
        mValue = itemView.findViewById(R.id.tv_value);
    }

    public void bind(Mock mock) {
        mName.setText(mock.getName());
        mValue.setText(mock.getValue());

        mId = mock.getValue();
    }

    public void setListener(ContactsAdapter.OnItemClickListener listener) {
        //здесь мы можем обратиться к элементу списка (ко всему элементу целиком)

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //при нажатии элемента списка вызовется метод onItemClick переданного onItemClickLestener-a
                //а вся логика нажатия задана(реализована) у нас в MainActivity
                listener.onItemClick(mId);
            }
        });
    }
}
