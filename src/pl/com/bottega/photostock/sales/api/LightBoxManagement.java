package pl.com.bottega.photostock.sales.api;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeClientRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeLightBoxRepository;
import pl.com.bottega.photostock.sales.infrastructure.repositories.FakeProductRepository;
import pl.com.bottega.photostock.sales.model.*;
import pl.com.bottega.photostock.sales.model.products.Picture;

import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by Slawek on 23/04/16.
 */
public class LightBoxManagement {
    private ClientRepository clientRepository = new FakeClientRepository();
    private ProductRepository productRepository = new FakeProductRepository();
    private LightBoxRepository lightBoxRepository = new FakeLightBoxRepository();
    private PurchaseProcess purchaseProcess = new PurchaseProcess();

    public String createLightbox(String clientNr, String name) {
        Client client = clientRepository.load(clientNr);
        LightBox lightBox = new LightBox(client);
        lightBox.changeName(name);
        lightBoxRepository.save(lightBox);
        return lightBox.getNumber();
    }

    public void add(String pictureId, String lightboxId) {
        Product product = productRepository.load(pictureId);
        checkState(product instanceof Picture, "%s is not a picture", pictureId);
        Picture picture = (Picture) product;

        LightBox lightBox = lightBoxRepository.load(lightboxId);
        lightBox.add(picture);

        lightBoxRepository.save(lightBox);
    }

    public void addAllToReservation(String lightboxId) {
        LightBox lightBox = lightBoxRepository.load(lightboxId);
        String clientNr = lightBox.getOwner().getNumber();

        for (Picture picture : lightBox.getItems()) {
            purchaseProcess.add(clientNr, picture.getNumber());
        }

        lightBoxRepository.save(lightBox);
    }

    public void addToReservation(String lightboxId, String pictureId) {
        LightBox lightBox = lightBoxRepository.load(lightboxId);
        String clientNr = lightBox.getOwner().getNumber();

        Picture picture = Iterables.find(lightBox.getItems(), new Predicate<Picture>() {
            @Override
            public boolean apply(Picture picture) {
                return picture.getNumber().equals(pictureId);
            }
        });
        checkNotNull(picture, "%s does not contain %s", lightboxId, pictureId);
        purchaseProcess.add(clientNr, picture.getNumber());
        lightBoxRepository.save(lightBox);
    }
}
