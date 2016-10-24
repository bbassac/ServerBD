package hello.repository;

import hello.bean.Bd;
import hello.bean.Collection;
import hello.bean.CollectionBuilder;
import hello.bean.Serie;
import org.apache.logging.log4j.util.Strings;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by b.bassac on 24/10/2016.
 */
public class ImageCrawlerTest {
static int i = -1;

    @Test
    public void crawlAllImages() throws FileNotFoundException {
        Collection collection = CollectionBuilder.getCollection(false);
        for (Serie serie : collection.getListeSerie()){
            crawlImage(serie.getImageUrl());
            for (Bd bd:serie.getListManquante()){
                crawlImage(bd.getCouvertureUrl());
            }
            for (Bd bd:serie.getListPossede()){
                crawlImage(bd.getCouvertureUrl());
            }
        }

        System.out.println(" Total images = " + i);
    }

    private void crawlImage(String imageUrl) throws FileNotFoundException {
        if(Strings.isNotBlank(imageUrl)){
            i++;
            getFile(imageUrl);
        }
    }

    public static void getFile(String host) throws FileNotFoundException
    {
        InputStream input = null;
        FileOutputStream writeFile = null;

        try
        {
            URL url = new URL(host);
            URLConnection connection = url.openConnection();
            int fileLength = connection.getContentLength();

            if (fileLength == -1)
            {
                System.out.println("Invalide URL or file " + host);
                return;
            }

            input = connection.getInputStream();
            String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            writeFile = new FileOutputStream("target/"+fileName);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
                writeFile.write(buffer, 0, read);
            writeFile.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error while trying to download the file "+host);
        }


        finally
        {
            try
            {
                writeFile.close();
                input.close();
            }
            catch (Exception e)
            {
                System.out.println("Error while trying to download the file "+host);
            }
        }
    }
}
