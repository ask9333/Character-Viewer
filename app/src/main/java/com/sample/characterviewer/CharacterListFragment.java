package com.sample.characterviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.characterviewer.Services.AsyncTaskForCharacterDetails;
import com.sample.characterviewer.databases.RelatedTopicsDatabase;

public class CharacterListFragment extends Fragment implements CharacterViewRecyclerViewAdapter.onItemClicked {
    private RelatedTopicsDatabase database;
    private CharacterViewRecyclerViewAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.character_fragment, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.characterRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CharacterViewRecyclerViewAdapter(getContext(), database.getRelatedTopicsDao().getAllNames(), database.getRelatedTopicsDao().getIconUrls(), this);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = RelatedTopicsDatabase.getInstance(getActivity());

    }

    @Override
    public void onStart() {
        super.onStart();
        if (database.getRelatedTopicsDao().getAllNames().isEmpty()) {
            new AsyncTaskForCharacterDetails(getActivity(), new AsyncTaskForCharacterDetails.Show() {
                @Override
                public void show() {
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public void close() {
                    progressBar.setVisibility(View.GONE);
                    setAdapter();
                }
            }).execute(BuildConfig.url);
        } else {
            setAdapter();
        }
    }

    protected void setAdapter() {
        // avoid setting adapter when you come back
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }
    }

    public void onChangeListener(String searchedString) {
        adapter.updateList(database.getRelatedTopicsDao().getSearchedNames(searchedString), database.getRelatedTopicsDao().getSearchedUrls(searchedString));
    }

    @Override
    public void onClick(String name) {
        Intent intent = new Intent(getActivity(), CharacterDetailActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }

}
