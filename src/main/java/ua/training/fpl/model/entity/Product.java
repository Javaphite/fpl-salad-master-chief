package ua.training.fpl.model.entity;

public class Product {

    private String name;
    private long calorificValue;
    private ProductCategory category;

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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public enum ProductCategory {
        VEGETABLE(true), MEAT(false), SEAFOOD(false), SEAFOOD_VEGAN(true), FRUIT(true), OTHER(false), OTHER_VEGAN(true);

        ProductCategory(boolean vegan) {
            this.vegan = vegan;
        }

        private final boolean vegan;

        public boolean isVegan() {
            return vegan;
        }
    }
}
