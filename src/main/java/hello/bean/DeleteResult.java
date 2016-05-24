package hello.bean;

/**
 * Created by b.bassac on 29/03/2016.
 */
public class DeleteResult {
    private int collectionDeleted;
    private int serieDeleted;
    private int bdDeleted;

    public int getCollectionDeleted() {
        return collectionDeleted;
    }

    public void setCollectionDeleted(int collectionDeleted) {
        this.collectionDeleted = collectionDeleted;
    }

    public int getSerieDeleted() {
        return serieDeleted;
    }

    public void setSerieDeleted(int serieDeleted) {
        this.serieDeleted = serieDeleted;
    }

    public int getBdDeleted() {
        return bdDeleted;
    }

    public void setBdDeleted(int bdDeleted) {
        this.bdDeleted = bdDeleted;
    }
}
