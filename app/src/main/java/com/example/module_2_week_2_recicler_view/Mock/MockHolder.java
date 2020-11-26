package com.example.module_2_week_2_recicler_view.Mock;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_2_week_2_recicler_view.R;

public class MockHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mValue;

    //itemView будет ссылаться  на li_mock (list item mock)
    public MockHolder(@NonNull View itemView) {
        super(itemView);

        mName = itemView.findViewById(R.id.tv_name);
        mValue = itemView.findViewById(R.id.tv_value);
    }

    public void bind(Mock mock) {
        mName.setText(mock.getName());
        mValue.setText(mock.getValue());
    }
}
