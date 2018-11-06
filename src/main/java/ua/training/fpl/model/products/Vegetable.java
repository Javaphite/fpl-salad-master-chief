package ua.training.fpl.model.products;

import java.util.EnumSet;
import java.util.Set;

import static ua.training.fpl.model.products.Product.PreparationMethod.*;

public class Vegetable extends Product {

    public Vegetable() {
        isVegan = true;
    }

    @Override
    public void setVegan(boolean vegan) {
        throw new UnsupportedOperationException("Vegetables always counts as vegan product!");
    }

    @Override
    public Set<PreparationMethod> getAvailablePreparationMethods() {
          return EnumSet.of(FRIED, STEWED, BOILED, SMASHED, WARM, FERMENTED, CIRCLE_SLICED, CUBES_SLICED, NONE);
    }
}
