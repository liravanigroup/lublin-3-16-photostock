package pl.com.bottega.photostock.sales.infrastructure.repositories;

import pl.com.bottega.photostock.sales.model.LightBox;
import pl.com.bottega.photostock.sales.model.LightBoxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Slawek on 14/05/16.
 */
public class FakeLightBoxRepository implements LightBoxRepository {
    private static Map<String, LightBox> fakeDatabase = new HashMap<>();

    @Override
    public LightBox load(String nr) {
        LightBox lightBox = fakeDatabase.get(nr);
        if (lightBox == null)
            throw new RuntimeException("lightBox " + nr + " does not extist");//TODO wprowadzić wyjątek DataDoesNotExistsException
        return lightBox;
    }

    @Override
    public void save(LightBox lightBox) {
        if (lightBox.getNumber() == null)
            lightBox.setNumber(UUID.randomUUID().toString());
        fakeDatabase.put(lightBox.getNumber(), lightBox);
    }
}
