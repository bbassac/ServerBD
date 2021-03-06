package hello.repository;

import hello.LogUtils;
import hello.bean.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by b.bassac on 26/05/2016.
 */
public class CustomCache {
    public static final String COLLECTION_CACHE = "collection";

    Map<String,Collection> map = new HashMap<>();

    public void putIn(String key, Collection collection) {
        LogUtils.warn("Put Collection in cache " + collection);
        map.put(key,collection);
    }

    public boolean isEmpty(String key) {
        boolean res = !map.containsKey(key) && map.get(key)==null;
        return res;
    }

    public Collection getCache(String key) {
        LogUtils.warn("Get In Cache");
        return map.get(key);
    }

    public void clearCache(String key){
        map.remove(key);
    }
}
