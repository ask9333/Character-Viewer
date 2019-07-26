package com.sample.characterviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterViewRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewRecyclerViewAdapter.NamesViewHolder> {

    private List<String> names;
    private List<String> iconUrl;
    private Context context;
    private onItemClicked onItemClicked;


    public CharacterViewRecyclerViewAdapter(Context context, List<String> names, List<String> iconUrl, onItemClicked onItemClicked) {
        this.iconUrl = iconUrl;
        this.names = names;
        this.context = context;
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_list_item, parent, false);
        return new NamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NamesViewHolder holder, final int position) {
        considerDisplayingIcon(holder, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked.onClick(names.get(position));
            }
        });
    }

    protected void considerDisplayingIcon(@NonNull NamesViewHolder holder, int position) {
        holder.characterName.setText(names.get(position));
        if (iconUrl.get(position) != null && !iconUrl.get(position).isEmpty()) {
            Picasso.get().load(iconUrl.get(position)).into(holder.characterImage);
        } else {
            holder.characterImage.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background));
        }
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public void updateList(List<String> searchedNames, List<String> iconUrls) {
        names.clear();
        iconUrl.clear();
        names.addAll(searchedNames);
        iconUrl.addAll(iconUrls);
        notifyDataSetChanged();
    }

    public interface onItemClicked {

        void onClick(String name);
    }

    public class NamesViewHolder extends RecyclerView.ViewHolder {

        private TextView characterName;
        private ImageView characterImage;

        public NamesViewHolder(@NonNull View itemView) {
            super(itemView);
            characterName = itemView.findViewById(R.id.characterName);
            characterImage = itemView.findViewById(R.id.characterImage);
        }
    }
}
