package hello.repository;

import hello.bean.Collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by b.bassac on 26/05/2016.
 */
public class CustomCache {

    Map<String,Collection> map = new HashMap<>();

    public void putIn(String key, Collection collection) {
        System.out.println("Put in cache " + collection);
        map.put(key,collection);
    }

    public boolean isEmpty(String key) {
        boolean res = !map.containsKey(key) && map.get(key)==null;
        return res;
    }

    public Collection getCache(String key) {
        System.out.println("Get In Cache");
        return map.get(key);
    }

    public void clearCache(String key){
        map.remove(key);
    }
}
