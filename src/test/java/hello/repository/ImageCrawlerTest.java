package hello.repository;

import hello.bean.Bd;
import hello.bean.Collection;
import hello.bean.CollectionBuilder;
import hello.bean.Serie;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.util.Strings;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by b.bassac on 24/10/2016.
 */
public class ImageCrawlerTest {


    @BeforeClass
    public void beforeClass() throws IOException {
        File directory = new File("target\\img");
        if(directory.exists()) {
            FileUtils.deleteDirectory(directory);
        }
        directory.mkdir();

    }

    @Test
    public void crawlAllImages() throws FileNotFoundException {
        int nbImgCrawled = -27;
        Collection collection = CollectionBuilder.getCollection(false);
        for (Serie serie : collection.getListeSerie()){
            if(Strings.isNotBlank(serie.getImageUrl())){

                getFile(serie.getImageUrl());
                nbImgCrawled++;
            }
            for (Bd bd:serie.getListManquante()){
                if(Strings.isNotBlank(bd.getCouvertureUrl())){

                    getFile(bd.getCouvertureUrl());
                    nbImgCrawled++;
                }
            }
            for (Bd bd:serie.getListPossede()){
                if(Strings.isNotBlank(bd.getCouvertureUrl())){

                    getFile(bd.getCouvertureUrl());
                    nbImgCrawled++;
                }
            }
        }

        System.out.println(" Total images = " + nbImgCrawled);
        Assert.assertEquals(nbImgCrawled, countImages("target\\img"));
        Assert.assertEquals(nbImgCrawled, countImages("src\\main\\resources\\static\\img\\couv"));
    }

    private int countImages(String dir) {
        File directory = new File(dir);
        File[] f = directory.listFiles();
        int x = 0;
        for (int i = 0 ; i < f.length ; i++) {
            if (f[i].isFile()) {
                x++;
            }
        }
        return x;
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
                Assert.fail("Invalide URL or file ");
            }

            input = connection.getInputStream();
            String fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            writeFile = new FileOutputStream("target/img/"+fileName);
            byte[] buffer = new byte[1024];
            int read;

            while ((read = input.read(buffer)) > 0)
                writeFile.write(buffer, 0, read);
            writeFile.flush();
        }
        catch (IOException e)
        {
            Assert.fail(e.getMessage());
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
                Assert.fail("Error while trying to download the file "+host);
            }
        }
    }
}
