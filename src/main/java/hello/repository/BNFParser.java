package hello.repository;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import hello.bean.SearchBD;
import net.minidev.json.JSONArray;


class BNFParser {


    private BNFParser() {
    }

    public static SearchBD parse(String source){



        String request = "$.srw:searchRetrieveResponse.srw:records.srw:record.srw:recordData.mxc:record.mxc:datafield";
        DocumentContext result = JsonPath.parse(source);


        int nbResult = result.read("$.srw:searchRetrieveResponse.srw:numberOfRecords");
        if (nbResult== 0){
            throw new BookNotFoundException();
        }

        JSONArray serie =   result.read(request+"[?(@.tag==290)].mxc:subfield[?(@.code == \"a\")].content");
        JSONArray titre =   result.read(request+"[?(@.tag==245)].mxc:subfield[?(@.code == \"a\")].content");
        JSONArray editeur = result.read(request+"[?(@.tag==\"260\")].mxc:subfield[?(@.code==\"c\")].content");
        JSONArray tome =    result.read(request+"[?(@.tag==290)].mxc:subfield[?(@.code == \"v\")].content");
        JSONArray numero =  result.read(request+"[?(@.tag==460)].mxc:subfield[?(@.code == \"v\")].content");
        JSONArray isbn =    result.read(request+"[?(@.tag==\"020\")].mxc:subfield[?(@.code == \"a\")].content");
        JSONArray auteurN = result.read(request+"[?(@.tag==\"100\")].mxc:subfield[?(@.code == \"a\")].content");
        JSONArray auteurP = result.read(request+"[?(@.tag==\"100\")].mxc:subfield[?(@.code == \"m\")].content");
        JSONArray anneePubli = result.read(request+"[?(@.tag==\"260\")].mxc:subfield[?(@.code == \"d\")].content");
        JSONArray notes = result.read(request+"[?(@.tag==\"300\")].mxc:subfield[?(@.code == \"a\")].content");

        SearchBD bd = new SearchBD();

        String nomAut = !auteurN.isEmpty()? auteurN.get(0).toString() : "";
        String prenomAut = !auteurP.isEmpty()? auteurP.get(0).toString() : "";

        bd.setTitre(!titre.isEmpty() ? titre.get(0).toString() : "");
        bd.setSerie(!serie.isEmpty() ? serie.get(0).toString() : "");
        bd.setEditeur(!editeur.isEmpty() ? editeur.get(0).toString() : "");
        bd.setTome(!tome.isEmpty() ? tome.get(0).toString() : "");
        bd.setNumero(!numero.isEmpty()? numero.get(0).toString() : "");
        bd.setIsbn(!isbn.isEmpty() ? isbn.get(0).toString() : "");
        bd.setAuteur(nomAut + " " +prenomAut);
        bd.setAnneePublication(!anneePubli.isEmpty() ? anneePubli.get(0).toString() : "");
        bd.setNotes(!notes.isEmpty() ? notes.get(0).toString() : "");
        return bd;
    }

  
}
