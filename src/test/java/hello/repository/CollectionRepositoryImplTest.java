package hello.repository;

import hello.bean.Collection;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.mockito.ArgumentMatchers.anyString;


public class CollectionRepositoryImplTest {

    @Test
    public void GetCollectionWithCache(){

        CollectionRepositoryImpl repo = new CollectionRepositoryImpl();
        CustomCache cache = new CustomCache();
        Collection collectionInCache = new Collection();
        cache.putIn(CustomCache.COLLECTION_CACHE, collectionInCache);
        repo.cache=cache;

        Collection result = repo.getCollection();

        Assert.assertEquals(result,collectionInCache);
    }

    @Test
    public void GetCollectionWithoutCache(){

        CollectionRepositoryImpl repo = new CollectionRepositoryImpl();
        repo.cache= new CustomCache();

        EntityManager mockEntityManager = Mockito.mock(EntityManager.class);
        Mockito.when(mockEntityManager.createQuery(anyString())).thenReturn(Mockito.mock(Query.class));
        repo.entityManager = mockEntityManager;
        Collection result = repo.getCollection();

        Assert.assertEquals(result,null);
    }

}