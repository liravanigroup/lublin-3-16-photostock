package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 12/03/16.
 */
public class LightBox {

    private String name;
    private Client owner;
    private Picture[] items = new Picture[2];
    private boolean closed = false;

    public void close(){
        this.closed = true;
    }

    public void changeName(String newName){
        if (!closed)
            this.name = newName;
    }

    /*
    public - modyfukiator dostępu
    static - opcjonanie aby metoda na rzecz klasy a nie obiektou
    void - typ zwracany, bez zwracania
    remove - nazwenik metody
    () - parametry
          Picture - typ
          pictureToRemove - nazewnik parametru
     */
    public void remove(Picture pictureToRemove){

    }

    public void add(Picture pictureToRemove){

    }

    public String getName() {
        return name;
    }
}
