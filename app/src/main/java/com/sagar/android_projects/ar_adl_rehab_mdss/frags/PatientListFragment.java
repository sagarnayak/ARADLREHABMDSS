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

import com.sagar.android_projects.ar_adl_rehab_mdss.R;
import com.sagar.android_projects.ar_adl_rehab_mdss.adapter.AdapterPatientList;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user.User;
import com.sagar.android_projects.ar_adl_rehab_mdss.retrofit.Models.user.UserListResponse;
import com.sagar.android_projects.ar_adl_rehab_mdss.singleton.AppSingleton;
import com.sagar.android_projects.ar_adl_rehab_mdss.core.Const;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;


/**
 * A simple {@link Fragment} subclass.
 */
public class PatientListFragment extends Fragment {

    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private boolean isLoading;
    private boolean isLastPage;
    private AdapterPatientList adapter;
    private ArrayList<User> users;


    public PatientListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_patients);
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
                        getDataFromServer(String.valueOf(users.size()), String.valueOf(Const.PAGE_SIZE));
                    }
                }
            }
        });
    }

    private void getDataFromServer(String offset, String pageSize) {
        isLoading = true;

        ((AppSingleton) getActivity().getApplicationContext()).getApiInterface()
                .userList(offset, pageSize)
                .enqueue(new Callback<UserListResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<UserListResponse> call,
                                           @NonNull Response<UserListResponse> response) {
                        try {
                            if (response.isSuccessful()) {
                                if (response.body().getData().size() < Const.PAGE_SIZE)
                                    adapter.setNoMoreDataAvailable(true);
                                if (users == null) {
                                    users = new ArrayList<>();
                                    linearLayoutManager = new LinearLayoutManager(getActivity());
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    recyclerView.setAdapter(new AdapterPatientList(users, (AdapterPatientList.AdapterPatientListCallback) getActivity()));
                                }
                                users.addAll(response.body().getData());
                                adapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        isLoading = false;
                    }

                    @Override
                    public void onFailure(Call<UserListResponse> call, Throwable t) {

                    }
                });
    }

}