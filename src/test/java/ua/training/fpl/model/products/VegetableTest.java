package ua.training.fpl.model.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ua.training.logger.TestLifecycleLogger;

import java.util.EnumSet;
import java.util.Set;

import static ua.training.fpl.model.products.Product.PreparationMethod.*;

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

    @Test
    @Tag("getAvailablePreparationMethods")
    public void availablePreparationMethodsOfVegetableNotContainsIrrelevantVariants() {
        Set<Product.PreparationMethod> irrelevantMethods =
                EnumSet.of(MINCE, CARAMELIZED, COLD);

        boolean containsIrrelevant = new Vegetable().getAvailablePreparationMethods()
                                        .stream()
                                        .map(irrelevantMethods::contains)
                                        .reduce((x,y) -> x||y)
                                        .get();

        Assertions.assertFalse(containsIrrelevant);
    }

    @Test
    @Tag("getAvailablePreparationMethods")
    public void availablePreparationMethodsOfVegetableContainsAllRelevantVariants() {
        Set<Product.PreparationMethod> relevantMethods =
                EnumSet.of(FRIED, STEWED, BOILED, SMASHED, WARM, FERMENTED, CIRCLE_SLICED, CUBES_SLICED, NONE);
        Set<Product.PreparationMethod> vegetableAvailableMethods = new Vegetable().getAvailablePreparationMethods();

        boolean containsOnlyRelevant = relevantMethods.stream()
                                            .map(vegetableAvailableMethods::contains)
                                            .reduce((x,y) -> x && y)
                                            .get();

        Assertions.assertTrue(containsOnlyRelevant);
    }
}
