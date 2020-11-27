package com.example.module_2_week_2_recicler_view.Mock;

//нужен для снабжения RecyclerView данными (является посредником между объектами и RecyclerView)

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_2_week_2_recicler_view.R;

import java.util.ArrayList;
import java.util.List;

public class MockAdapter extends RecyclerView.Adapter<MockHolder> {

    //храним данные
    private final List<Mock> mMockList = new ArrayList<>();


    @NonNull
    @Override
    //возвращает viewHolder, в котором хранятся элементы списка
    public MockHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //в parent хранится контекст

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.li_mock, parent, false);
        return new MockHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MockHolder holder, int position) {
        holder.bind(mMockList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMockList.size();
    }

    public void addData(List<Mock> mocks, boolean refreshFlag) {

        if (refreshFlag) {
            mMockList.clear();
        }

        mMockList.addAll(mocks);

        //уведомляем об изменении данных
        notifyDataSetChanged();
    }
}
