package ua.training.fpl.model.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ua.training.logger.TestLifecycleLogger;

import java.util.EnumSet;
import java.util.Set;

import static ua.training.fpl.model.products.Product.PreparationMethod.NONE;
import static ua.training.fpl.model.products.Product.PreparationMethod.WARM;

public class SauceTest extends TestLifecycleLogger {

    @Test
    @Tag("getAvailablePreparationMethods")
    void availablePreparationMethodsOfSauceNotContainsIrrelevantVariants() {
        Set<Product.PreparationMethod> irrelevantMethods = EnumSet.complementOf(EnumSet.of(NONE, WARM));

        boolean containsIrrelevant = new Sauce().getAvailablePreparationMethods()
                .stream()
                .map(irrelevantMethods::contains)
                .reduce((x,y) -> x||y)
                .get();

        Assertions.assertFalse(containsIrrelevant);
    }

    @Test
    @Tag("getAvailablePreparationMethods")
    void availablePreparationMethodsOfSauceContainsAllRelevantVariants() {
        Set<Product.PreparationMethod> relevantMethods = EnumSet.of(NONE, WARM);
        Set<Product.PreparationMethod> vegetableAvailableMethods = new Sauce().getAvailablePreparationMethods();

        boolean containsOnlyRelevant = relevantMethods.stream()
                .map(vegetableAvailableMethods::contains)
                .reduce((x,y) -> x && y)
                .get();

        Assertions.assertTrue(containsOnlyRelevant);
    }
}
