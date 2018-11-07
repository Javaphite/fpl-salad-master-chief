package ua.training.fpl.model.products;

import java.util.EnumSet;
import java.util.Set;

import static ua.training.fpl.model.products.Product.PreparationMethod.NONE;
import static ua.training.fpl.model.products.Product.PreparationMethod.WARM;

public class Sauce extends Product {

    @Override
    public Set<PreparationMethod> getAvailablePreparationMethods() {
        return EnumSet.of(WARM, NONE);
    }
}
