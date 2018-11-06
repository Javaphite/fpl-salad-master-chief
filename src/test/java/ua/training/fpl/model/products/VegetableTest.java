package ua.training.fpl.model.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ua.training.logger.TestLifecycleLogger;

public class VegetableTest extends TestLifecycleLogger {

    @Test
    @Tag("constructor")
    public void vegetableAlwaysVeganAfterConstruction() {
        Product vegetable = new Vegetable();

        Assertions.assertTrue(vegetable.isVegan());
    }

    @Test
    @Tag("setVegan")
    public void setVeganAlwaysThrowsUnsupportedOperationException() {
        Product vegetable = new Vegetable();

        Assertions.assertAll(
                ()-> Assertions.assertThrows(UnsupportedOperationException.class, () -> vegetable.setVegan(true)),
                ()-> Assertions.assertThrows(UnsupportedOperationException.class, () -> vegetable.setVegan(false))
         );
    }

    // TODO: implement test - limit preparation methods available
}
