package ua.training.fpl.model.products;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.EnumSet;
import java.util.Set;

public abstract class Product {

    public static final long CALORIES_PER_WEIGHT = 100;

    protected String name;
    protected long calorificValue;
    protected boolean isVegan;

    public enum PreparationMethod {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(calorificValue, product.calorificValue)
                .append(isVegan, product.isVegan)
                .append(name, product.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(calorificValue)
                .append(isVegan)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("calorificValue", calorificValue)
                .append("isVegan", isVegan)
                .toString();
    }
}
