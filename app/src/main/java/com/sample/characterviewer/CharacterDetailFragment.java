package com.sample.characterviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.sample.characterviewer.databases.DbRelatedTopics;
import com.sample.characterviewer.databases.RelatedTopicsDatabase;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class CharacterDetailFragment extends Fragment {

    private TextView characterDetailsName;
    private TextView firstUrl;
    private String name;
    private String iconUrl;
    private TextView nameDescription;
    private String url;
    private ImageView characterImage;
    private String result;
    private RelatedTopicsDatabase database;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.character_detail_fragment, container, false);
        characterDetailsName = view.findViewById(R.id.characterDetailsName);
        nameDescription = view.findViewById(R.id.nameDescription);
        characterImage = view.findViewById(R.id.characterImage);
        firstUrl = view.findViewById(R.id.firstUrl);
        firstUrl.setText(url);
        characterDetailsName.setText(name);
        nameDescription.setText(result);
        if (iconUrl != null && !iconUrl.isEmpty()) {
            Picasso.get().load(iconUrl).into(characterImage);
        } else {
            characterImage.setImageDrawable(ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.ic_launcher_background));
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = RelatedTopicsDatabase.getInstance(getActivity());
        if (Objects.requireNonNull(getActivity()).getIntent() != null) {
            name = getActivity().getIntent().getStringExtra("name");
            DbRelatedTopics relatedTopics = database.getRelatedTopicsDao().getSelectedTopic(name);
            url = relatedTopics.getFirstURL();
            iconUrl = relatedTopics.getIconDetails().getUrl();
            result = relatedTopics.getCharacterDescription();
        }
    }
}
