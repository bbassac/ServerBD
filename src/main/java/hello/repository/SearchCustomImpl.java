package hello.repository;


import com.github.kevinsawicki.http.HttpRequest;
import hello.LogUtils;
import hello.bean.SearchBD;
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
    public SearchBD getBookFromIsbn(String id)  {
        String url = formatUrl(isbnUrl,id);
        String response =  HttpRequest.get(url).body();
        return convertToJson(response);
    }

    @Override
    public SearchBD getBookFromEanIsbn(String id)  {
        String url = formatUrl(eanUrl,id);
        String response =  HttpRequest.get(url).body();
        return convertToJson(response);
    }

    private SearchBD convertToJson(String xml) {
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
       return BNFParser.parse(jsonPrettyPrintString);

    }

    private String formatUrl(String baseUrl, String id){
        String url = baseUrl.replace(template,id);
        url =  url.replace("-","");
        LogUtils.warn("Requesting " + url);
        return url;
    }


}
