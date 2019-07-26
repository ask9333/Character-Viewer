package com.sample.characterviewer.Services;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.sample.characterviewer.databases.DbIconDetails;
import com.sample.characterviewer.databases.DbRelatedTopics;
import com.sample.characterviewer.databases.RelatedTopicsDatabase;
import com.sample.characterviewer.models.CharacterInfo;
import com.sample.characterviewer.models.RelatedTopics;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskForCharacterDetails extends AsyncTask<String, Void, CharacterInfo> {

    private final Context context;
    private CharacterInfo characterInfo;
    private Show show;

    public AsyncTaskForCharacterDetails(Context context, Show show) {
        this.context = context;
        this.show = show;
    }

    private String getTextFrom(@NonNull String htmlText) {
        return Html.fromHtml(htmlText).toString();
    }

    @NonNull
    private String getTitle(String result) {
        String title = getTextFrom(result);
        int index = title.indexOf("\n");
        return index < 0 ? title : title.substring(0, index);
    }


    @NonNull
    private String getDescription(String result) {
        String title = getTextFrom(result);
        int index = title.indexOf("\n");
        String description = index < 0 ? title : title.substring(index);
        return description.replaceAll("\n+", "");
    }

    @Override
    protected CharacterInfo doInBackground(String... urls) {

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            BufferedReader bf = new BufferedReader((inputStreamReader));
            String value = bf.readLine();

            Gson gson = new Gson();
            characterInfo = gson.fromJson(value, CharacterInfo.class);
            RelatedTopicsDatabase database = RelatedTopicsDatabase.getInstance(context);
            database.clearAllTables();
            for (RelatedTopics relatedTopics : characterInfo.getRelatedTopics()) {
                DbRelatedTopics dbRelatedTopics = new DbRelatedTopics();
                DbIconDetails dbIconDetails = new DbIconDetails();
                dbIconDetails.setUrl(relatedTopics.getIconDetails().getURL());
                dbRelatedTopics.setFirstURL(relatedTopics.getFirstURL());
                dbRelatedTopics.setCharacterDescription(getDescription(relatedTopics.getResult()));
                dbRelatedTopics.setCharacterName(getTitle(relatedTopics.getResult()));
                dbRelatedTopics.setIconDetails(dbIconDetails);
                database.getRelatedTopicsDao().addRelatedTopics(dbRelatedTopics);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return characterInfo;
    }

    @Override
    protected void onPostExecute(CharacterInfo characterInfo) {
        super.onPostExecute(characterInfo);
        show.close();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        show.show();
    }

    public interface Show {

        void show();

        void close();
    }
}
