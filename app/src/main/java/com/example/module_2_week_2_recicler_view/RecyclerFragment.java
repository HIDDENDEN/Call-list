package com.example.module_2_week_2_recicler_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.module_2_week_2_recicler_view.Mock.MockAdapter;
import com.example.module_2_week_2_recicler_view.Mock.MockGenerator;

public class RecyclerFragment extends Fragment {

    //наш список
    private RecyclerView mRecycler;
    private final MockAdapter mMockAdapter = new MockAdapter();

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //находим спсиок
        mRecycler = view.findViewById(R.id.recycler);
    }

    //когда уже есть доступ к контексту
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mMockAdapter);
        mMockAdapter.addData(MockGenerator.Generate(20));
    }
}
