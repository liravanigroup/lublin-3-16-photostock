package pl.com.bottega.photostock.sales.model;

/**
 * Created by Slawek on 12/03/16.
 */
public class LightBox {

    private String name;
    private Client owner;
    private Picture[] items = new Picture[5];
    private boolean closed = false;

    public LightBox(Client owner){
        this.owner = owner;
    }

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

    public void add(Picture pictureToAdd){
        //sprawdzamy czy items zawiera już ten picture

        int coursor = 0;

        while (coursor < items.length){
            Picture pic = items[coursor];
            if (pic != null){
                String nr1 = pic.getNumber();
                String nr2 = pictureToAdd.getNumber();

                if (nr1.equals(nr2)){
                    throw new IllegalArgumentException("lightbox already contains picture " + pictureToAdd.getNumber());
                }
            }

            //!!!!!
            coursor++;
        }

        coursor = 0;

        while(true){
            Picture reference = items[coursor];
            if (reference == null){
                items[coursor] = pictureToAdd;
                break;//break  !!!!!
            }
            coursor++;//!!!!!
        }


    }

    public String getName() {
        return name;
    }
}
