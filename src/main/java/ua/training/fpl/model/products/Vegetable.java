package ua.training.fpl.model.products;

import java.util.EnumSet;
import java.util.Set;

import static ua.training.fpl.model.products.Product.PreparationMethod.*;

public class Vegetable extends Product implements SaladComponent {

    @Override
    public Set<PreparationMethod> getAvailablePreparationMethods() {
        // TODO: complete list after method list in super completed
        return EnumSet.of(WHOLE, BOILED);
    }
}
