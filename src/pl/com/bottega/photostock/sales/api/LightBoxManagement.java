package pl.com.bottega.photostock.sales.api;

import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeLightBoxRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Picture;

/**
 * Created by Slawek on 23/04/16.
 */
public class LightBoxManagement {
    private ClientRepository clientRepository = new FakeClientRepository();
    private ProductRepository productRepository = new FakeProductRepository();
    private LightBoxRepository lightBoxRepository = new FakeLightBoxRepository();
    private PurchaseProcess purchaseProcess = new PurchaseProcess();

    public String createLightbox(String clientNr, String name){
        Client client = clientRepository.load(clientNr);
        LightBox lightBox = new LightBox(client);
        lightBox.changeName(name);
        lightBoxRepository.save(lightBox);
        return lightBox.getNumber();
    }

    public void add(String pictureId, String lightboxId){
        Product product = productRepository.load(pictureId);
        if (product instanceof Picture) {
            Picture picture = (Picture) product;

            LightBox lightBox = lightBoxRepository.load(lightboxId);
            lightBox.add(picture);

            lightBoxRepository.save(lightBox);
        }
        else{
            throw new IllegalArgumentException(pictureId + " is not a picture");
        }
    }

    public void addAllToReservation(String lightboxId){
        LightBox lightBox = lightBoxRepository.load(lightboxId);
        String clientNr = lightBox.getOwner().getNumber();

        for (Picture picture : lightBox.getItems()){
            purchaseProcess.add(clientNr, picture.getNumber());
        }

        lightBoxRepository.save(lightBox);
    }

    public void addToReservation(String lightboxId, String pictureId){
        LightBox lightBox = lightBoxRepository.load(lightboxId);
        String clientNr = lightBox.getOwner().getNumber();

        for (Picture picture : lightBox.getItems()){
            if (picture.getNumber().equals(pictureId)) {
                purchaseProcess.add(clientNr, picture.getNumber());
                lightBoxRepository.save(lightBox);
                return;
            }
        }

        throw new IllegalArgumentException(lightboxId + " does not contain " + pictureId);
    }
}
