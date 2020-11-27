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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.module_2_week_2_recicler_view.Mock.MockAdapter;
import com.example.module_2_week_2_recicler_view.Mock.MockGenerator;

import java.util.Random;

public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    //наш список
    private RecyclerView mRecycler;

    //добавляем refresher в качестве поля
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private final MockAdapter mMockAdapter = new MockAdapter();

    //инициализируем view_error
    private View mErrorView;

    private Random mRandom = new Random();

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //находим спсиок
        mRecycler = view.findViewById(R.id.recycler);

        //привязываем refresher
        mSwipeRefreshLayout = view.findViewById(R.id.refresher);

        //связываем реализацию onRefreshListener с нашим Layout-ом
        mSwipeRefreshLayout.setOnRefreshListener(this);

        //инициализируем view_error
        mErrorView = view.findViewById(R.id.error_view);
    }

    //когда уже есть доступ к контексту
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mMockAdapter);


    }

    //вызывается, когда пользователь дергает пальцем по экрану и отпускает
    @Override
    public void onRefresh() {

        //post на любом view элементе работает, как любой handler на main треде. Просто постит сообщение в message queue
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {

                int count = mRandom.nextInt(4);

                if (count == 0) {
                    showError();
                } else {
                    showData(count);
                }


                //прячем индикатор прогресса
                if (mSwipeRefreshLayout.isRefreshing()) {//т.е. крутится прямо сейчас
                    mSwipeRefreshLayout.setRefreshing(false);//убрали прокрутку thumb-a
                }
            }
        }, 2000);
    }

    private void showData(int count) {
        mMockAdapter.addData(MockGenerator.Generate(5), true);
        mErrorView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
    }

    private void showError() {
        mErrorView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }
}
