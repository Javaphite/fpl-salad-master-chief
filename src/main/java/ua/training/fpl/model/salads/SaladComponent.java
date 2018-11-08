package ua.training.fpl.model.salads;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.training.fpl.model.products.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.Comparator;

public class SaladComponent {

    public static final Comparator<SaladComponent> CALORIFIC_VALUE_COMPARATOR =
            Comparator.comparingLong(component -> component.getProduct().getCalorificValue());

    private final Product product;
    private long weight;
    private Set<Product.PreparationMethod> preparationSteps;

    public SaladComponent(Product product, long weight, Product.PreparationMethod... preparationSteps) {
        this.product = product;
        this.weight = weight;

        if (preparationSteps.length == 0 ) {
            this.preparationSteps = EnumSet.of(Product.PreparationMethod.NONE);
        } else {
            this.preparationSteps = EnumSet.copyOf(Arrays.asList(preparationSteps));
        }
    }

    public Product getProduct() {
        return product;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getCalories() {
        return product.getCalorificValue()*weight/Product.CALORIES_PER_WEIGHT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SaladComponent that = (SaladComponent) o;

        return new EqualsBuilder()
                .append(product, that.product)
                .append(preparationSteps, that.preparationSteps)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(product)
                .append(preparationSteps)
                .toHashCode();
    }

    public Set<Product.PreparationMethod> getPreparationSteps() {
            return Collections.unmodifiableSet(preparationSteps);
        }
}
