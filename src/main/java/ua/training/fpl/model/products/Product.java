package ua.training.fpl.model.products;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

abstract class Product {

    protected String name;
    protected long calorificValue;
    boolean isVegan;
    Set<PreparationMethod> preparationSteps;

    protected enum PreparationMethod {
        FRIED,
        BOILED,
        STEWED,
        WARM,
        COLD,
        SMASHED,
        CIRCLE_SLICED,
        CUBES_SLICED,
        MINCE,
        FERMENTED,
        CARAMELIZED,
        NONE;
    }

    public Set<PreparationMethod> getAvailablePreparationMethods() {
        return EnumSet.allOf(PreparationMethod.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCalorificValue() {
        return calorificValue;
    }

    public void setCalorificValue(long calorificValue) {
        this.calorificValue = calorificValue;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public Set<PreparationMethod> getPreparationSteps() {
        return Collections.unmodifiableSet(preparationSteps);
    }

    public void setPreparationSteps(Set<PreparationMethod> preparationSteps) {
        this.preparationSteps = preparationSteps;
    }

    public void addPreparationSteps(Collection<PreparationMethod> preparationSteps) {
        this.preparationSteps.addAll(preparationSteps);
    }

    public boolean addPreparationStep(PreparationMethod step) {
       return preparationSteps.add(step);
    }

    public void removePreparationSteps(Collection<PreparationMethod> preparationSteps) {
        this.preparationSteps.removeAll(preparationSteps);
    }

    public boolean removePreparationStep(PreparationMethod step) {
        return preparationSteps.remove(step);
    }
}
