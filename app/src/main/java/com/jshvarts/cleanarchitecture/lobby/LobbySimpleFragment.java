package com.jshvarts.cleanarchitecture.lobby;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jshvarts.cleanarchitecture.R;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

public class LobbySimpleFragment extends Fragment {

    @Inject
    protected LobbyRepository lobbyRepository;

    @Inject
    protected LobbyFragmentRepository lobbyFragmentRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lobby_simple_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Timber.d("aaa - onViewCreated");


        String report = lobbyRepository.getReport();
        Timber.d("aaa: " + report);

        List<String> fragmentValues = lobbyFragmentRepository.getAllItems();
        for (String val : fragmentValues) {
            Timber.d("aaa: " + val);
        }
    }
}

