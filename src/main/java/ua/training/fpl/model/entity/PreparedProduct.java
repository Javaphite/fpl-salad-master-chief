package ua.training.fpl.model.entity;

public class PreparedProduct {

    private Product product;
    private PreparationMethod preparationMethod;

    public PreparedProduct(Product product, PreparationMethod preparationMethod) {
        this.product = product;
        this.preparationMethod = preparationMethod;
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
