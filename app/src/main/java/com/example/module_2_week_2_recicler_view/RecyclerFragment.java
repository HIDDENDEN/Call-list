package com.example.module_2_week_2_recicler_view;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.Random;

public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    //наш список
    private RecyclerView mRecycler;

    //добавляем refresher в качестве поля
    private SwipeRefreshLayout mSwipeRefreshLayout;
    //    private final MockAdapter mMockAdapter = new MockAdapter();
    private final ContactsAdapter mContactsAdapter = new ContactsAdapter();
    //инициализируем view_error
    private View mErrorView;

    private Random mRandom = new Random();

    //поле с lister-ом. Мы передаем его из Activity во фрагмент. Затем передадим его в Adapter (в onActivityCreated)
    private ContactsAdapter.OnItemClickListener mListener;

    //записываем значение в поле
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ContactsAdapter.OnItemClickListener){
            mListener = (ContactsAdapter.OnItemClickListener) context;
        }
    }

    //зануляем поле
    @Override
    public void onDetach() {
        mListener=null;//чтобы упростить жизнь сборщику мусора
        super.onDetach();
    }

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
        mRecycler.setAdapter(mContactsAdapter);
        mContactsAdapter.setListener(mListener);



    }

    //вызывается, когда пользователь дергает пальцем по экрану и отпускает
    @Override
    public void onRefresh() {

        //создаем Loader
        getLoaderManager().restartLoader(0, null, this);
    }


    // курсор - это таблица
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        //создаем Loader  и передаем его в систему
        return new CursorLoader(getActivity(),
                ContactsContract.Contacts.CONTENT_URI,//строка, по которой можно обратиться к content provider -у
                new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME},//   - столбцы таблицы которые нам надо получить
                null,//selection какие столбцы нас удовлетворяют (null  - значит все берем)
                null,//selection args
                ContactsContract.Contacts._ID// сортировка
        );

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mContactsAdapter.swapCursor(data);

        //после того, как данные загружены -> убираем индикатор загрузки

//        прячем индикатор прогресса
        if (mSwipeRefreshLayout.isRefreshing()) {//т.е. крутится прямо сейчас
            mSwipeRefreshLayout.setRefreshing(false);//убрали прокрутку thumb-a
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
