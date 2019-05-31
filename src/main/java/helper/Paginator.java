package helper;

public class Paginator {

    private int collectionSize;
    private int pageSize;
    private int currentStart;


    public void configure(int pageSize, int collectionSize){
        this.collectionSize = collectionSize;
        this.pageSize = pageSize;
        if (currentStart >= collectionSize){
            currentStart = 0;
        }
    }
    public Paginator(int pageSize, int size) {
        this.collectionSize = size;
        this.pageSize = pageSize;
        this.currentStart = 0;
    }

    public Paginator() {
        this.currentStart = 0;
    }

    public int getFrom() {
        return currentStart;
    }

    public int getTo() {
        return currentStart + pageSize - 1 < collectionSize ? currentStart + pageSize - 1: collectionSize - 1;
    }

    public void nextPage() {
        currentStart = currentStart + pageSize < collectionSize ? currentStart + pageSize : currentStart;
    }

    public void prevPage() {
        currentStart = currentStart - pageSize >= 0 ? currentStart - pageSize : 0;
    }

    public void startPage(){
        currentStart = getStartFrom();
    }

    public void lastPage(){
        currentStart = getLastFrom();
    }

    public int getNextFrom() {
        return currentStart + pageSize  < collectionSize ? currentStart + pageSize  : currentStart;
    }

    public int getNextTo() {
        return currentStart + 2*pageSize < collectionSize ? currentStart + 2*pageSize  : collectionSize - 1;
    }

    public int getPrevFrom() {
        return currentStart - pageSize - 1 >= 0 ? currentStart - pageSize : 0;
    }

    public int getPrevTo() {
        return currentStart - 1 >= 0 ? currentStart - 1 : 0;
    }

    public int getStartFrom(){
        return 0;
    }

    public int getStartTo(){
        return pageSize > collectionSize ? collectionSize - 1 : pageSize -1;
    }

    public int getLastFrom(){
        return (collectionSize / pageSize) * pageSize;
    }

    public int getLastTo(){
        return collectionSize - 1;
    }

    public boolean isLastPage(){
        return currentStart + pageSize >= collectionSize;
    }

    public boolean isFirstPag(){
        return currentStart == 0;
    }

    public int getPage(){
        return currentStart/pageSize + 1;
    }

}