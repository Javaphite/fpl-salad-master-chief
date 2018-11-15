package ua.training.fpl.model.entity;

/**
 * Entity for prepared product representation.
 */
public class PreparedProduct {

    private int id;
    private Product product;
    private PreparationMethod preparationMethod;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PreparationMethod getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(PreparationMethod preparationMethod) {
        this.preparationMethod = preparationMethod;
    }

    public enum PreparationMethod {
        RAW, FRIED, BOILED, STEWED, FERMENTED;
    }
}
