package com.sagar.android_projects.ar_adl_rehab_mdss.frags;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterGameList;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game.Game;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.game.GameList;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.util.NetworkUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends Fragment {

    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private boolean isLoading;
    private boolean isLastPage;
    private AdapterGameList adapter;
    private ArrayList<Game> gameLists;

    public GameListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_games);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDataFromServer("0", String.valueOf(Const.PAGE_SIZE));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        getDataFromServer(String.valueOf(gameLists.size()), String.valueOf(Const.PAGE_SIZE));
                    }
                }
            }
        });
    }

    private void getDataFromServer(String offset, String pageSize) {
        if (!NetworkUtil.isConnected(getActivity())) {
            Toast.makeText(getActivity(), "Please connect to internet", Toast.LENGTH_SHORT).show();
            return;
        }
        isLoading = true;
        ((AppSingleton) getActivity().getApplicationContext()).getApiInterface()
                .gameList(offset, pageSize)
                .enqueue(new Callback<GameList>() {
                    @Override
                    public void onResponse(@NonNull Call<GameList> call, @NonNull Response<GameList> response) {
                        try {
                            if (response.isSuccessful()) {
                                if (gameLists == null) {
                                    gameLists = new ArrayList<>();
                                    linearLayoutManager = new LinearLayoutManager(getActivity());
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    adapter = new AdapterGameList(gameLists);
                                    recyclerView.setAdapter(adapter);
                                }
                                if (response.body().getData().size() < Const.PAGE_SIZE)
                                    adapter.setNoMoreDataAvailable(true);
                                gameLists.addAll(response.body().getData());
                                adapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        isLoading = false;
                    }

                    @Override
                    public void onFailure(Call<GameList> call, Throwable t) {
                        isLoading = false;
                    }
                });
    }
}
