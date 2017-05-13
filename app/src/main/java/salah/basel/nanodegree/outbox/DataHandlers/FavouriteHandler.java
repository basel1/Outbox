package salah.basel.nanodegree.outbox.DataHandlers;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import salah.basel.nanodegree.outbox.Logging.L;
import salah.basel.nanodegree.outbox.Model.Article;

/**
 * Created by Basel on 12/05/2017.
 */

public class FavouriteHandler {
    private Context mCtx;
    private ArrayList<Article> Articles;

    public FavouriteHandler(Context context) {
        mCtx = context;
    }

    public void saveArticle(Article article) {
        getArticlesData();
        if (Articles == null)
            Articles = new ArrayList<>();
        Articles.add(article);
        saveArticleData(Articles);
    }

    private void saveArticleData(ArrayList<Article> Articles) {

        File folder = mCtx.getExternalFilesDir("Article-data");
        File sessionFile = new File(folder, "Article_data.txt");
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(sessionFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(Articles);
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Article> getArticlesData() {

        ArrayList<Article> data = null;
        if (Articles == null) {
            File folder = mCtx.getExternalFilesDir("Article-data");
            File sessionFile = new File(folder, "Article_data.txt");
            ObjectInputStream ois =
                    null;
            try {
                ois = new ObjectInputStream(new FileInputStream(sessionFile));
                try {
                    data = ((ArrayList<Article>) ois.readObject());
                    ois.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                L.m("file not created");
            }


            if (data != null) {
                L.m("data retrieved from file " + data.size());
                Articles = data;
            }
        }
        return Articles;
    }

}
