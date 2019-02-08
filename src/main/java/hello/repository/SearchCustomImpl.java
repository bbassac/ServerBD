package hello.repository;


import com.github.kevinsawicki.http.HttpRequest;
import hello.LogUtils;
import hello.bean.SearchBD;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Repository;

@Repository
public class SearchCustomImpl implements SearchCustom {
    String template="XXXX";

    String eanUrl ="http://catalogue.bnf.fr/api/SRU?version=1.2&operation=searchRetrieve&query=bib.ean%20adj%20\"XXXX\"&recordSchema=intermarcXchange";
    String isbnUrl="http://catalogue.bnf.fr/api/SRU?version=1.2&operation=searchRetrieve&query=bib.isbn%20adj%20\"XXXX\"&recordSchema=intermarcXchange";
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;


    @Override
    public SearchBD getBookFromIsbn(String id) throws BookNotFoundException {
        String url = formatUrl(isbnUrl,id);
        String response =  HttpRequest.get(url).body();
        return convertToJson(response);
    }

    @Override
    public SearchBD getBookFromEanIsbn(String id) throws BookNotFoundException {
        String url = formatUrl(eanUrl,id);
        String response =  HttpRequest.get(url).body();
        return convertToJson(response);
    }

    private SearchBD convertToJson(String xml) throws BookNotFoundException {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        parseJson(xmlJSONObj);
        //String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
       return parseJson(xmlJSONObj);

    }

    private String formatUrl(String baseUrl, String id){
        String url = baseUrl.replace(template,id);
        url =  url.replace("-","");
        LogUtils.warn("Requesting " + url);
        return url;
    }

    private SearchBD parseJson(JSONObject jsonObj) throws BookNotFoundException {
        int nbFound = (int) jsonObj.query("/srw:searchRetrieveResponse/srw:numberOfRecords");
        if (nbFound == 0){
            throw new BookNotFoundException();
        }
        JSONArray array = (JSONArray) jsonObj.query("/srw:searchRetrieveResponse/srw:records/srw:record/srw:recordData/mxc:record/mxc:datafield");
        SearchBD bd = new SearchBD();
        int TITLE_TAG = 245;
        String TITLE_CODE = "a";
        String CONTENT_TAG="content";
        String NUMERO_CODE = "u";

        for (int i =0; i< array.length();i++){
            JSONObject o = array.getJSONObject(i);
            if (o.has("tag") &&  o.get("tag").equals(TITLE_TAG)){
                JSONArray myarray = o.getJSONArray("mxc:subfield");
                for (int j = 0;j< myarray.length();j++){

                    if (myarray.getJSONObject(j).getString("code").equals(TITLE_CODE)){
                        bd.setTitre(myarray.getJSONObject(j).getString(CONTENT_TAG));
                    }

                    if (myarray.getJSONObject(j).getString("code").equals(NUMERO_CODE)){
                        bd.setTome(myarray.getJSONObject(j).getString(CONTENT_TAG));
                    }
                }

            }
        }

        return bd;
    }

}
