package hello.repository;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import hello.bean.SearchBD;
import net.minidev.json.JSONArray;


public class BNFParser {




    public static SearchBD parse(String source){



        String request = "$.srw:searchRetrieveResponse.srw:records.srw:record.srw:recordData.mxc:record.mxc:datafield";
        DocumentContext result = JsonPath.parse(source);


        int nbResult = result.read("$.srw:searchRetrieveResponse.srw:numberOfRecords");
        if (nbResult== 0){
            throw new BookNotFoundException();
        }

        JSONArray serie = result.read(request+"[?(@.tag==290)].mxc:subfield[?(@.code == \"a\")].content");
        JSONArray titre = result.read(request+"[?(@.tag==245)].mxc:subfield[?(@.code == \"a\")].content");
        JSONArray editeur = result.read(request+"[?(@.tag==\"260\")].mxc:subfield[?(@.code==\"c\")].content");
        JSONArray tome = result.read(request+"[?(@.tag==290)].mxc:subfield[?(@.code == \"v\")].content");
        JSONArray numero = result.read(request+"[?(@.tag==460)].mxc:subfield[?(@.code == \"v\")].content");
        JSONArray isbn = result.read(request+"[?(@.tag==\"020\")].mxc:subfield[?(@.code == \"a\")].content");

        SearchBD bd = new SearchBD();

        bd.setTitre(titre.size()>0 ? titre.get(0).toString() : "");
        bd.setSerie(serie.size()>0 ? serie.get(0).toString() : "");
        bd.setEditeur(editeur.size()>0 ? editeur.get(0).toString() : "");
        bd.setTome(tome.size()>0 ? tome.get(0).toString() : "");
        bd.setNumero(numero.size()>0 ? numero.get(0).toString() : "");
        bd.setIsbn(isbn.size()>0 ? isbn.get(0).toString() : "");
        return bd;
    }

  
}
