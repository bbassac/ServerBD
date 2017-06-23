package hello;

import hello.bean.Collection;
import hello.bean.CollectionBuilder;
import hello.repository.CollectionRepositoryCustom;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * Created by b.bassac on 23/06/2017.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    CollectionRepositoryCustom customRepo;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        LogUtils.warn("######################################");
        LogUtils.warn("#              LOADER                #");
        LogUtils.warn("######################################");
        LogUtils.warn("#       DELETE COLLECTIONS           #");
        LogUtils.warn("######################################");
        customRepo.deleteCollection();
        LogUtils.warn("#       CREATE COLLECTION            #");
        Collection collection = CollectionBuilder.getCollection(false);
        customRepo.createCollection(collection);
        LogUtils.warn("######################################");

    }
}
