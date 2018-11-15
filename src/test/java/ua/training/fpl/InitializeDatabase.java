package ua.training.fpl;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.model.dao.PreparedProductDao;
import ua.training.fpl.model.dao.ProductDao;
import ua.training.fpl.model.dao.RecipeDao;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Product;
import ua.training.fpl.model.entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class InitializeDatabase {

    private static List<Recipe> recipes = new ArrayList<>();

    public static void init(String[] args) {
        // PRODUCTS:
        Product eggs = new Product();
        eggs.setName("Eggs");
        eggs.setCalorificValue(155);
        eggs.setCategory(Product.ProductCategory.OTHER);

        Product oliveOil = new Product();
        oliveOil.setName("Olive oil");
        oliveOil.setCalorificValue(884);
        oliveOil.setCategory(Product.ProductCategory.OTHER_VEGAN);

        Product tomato = new Product();
        tomato.setName("Tomato");
        tomato.setCalorificValue(18);
        tomato.setCategory(Product.ProductCategory.VEGETABLE);

        Product lettuce = new Product();
        lettuce.setName("Lettuce");
        lettuce.setCalorificValue(13);
        lettuce.setCategory(Product.ProductCategory.VEGETABLE);

        Product cauliflower = new Product();
        cauliflower.setName("Cauliflower");
        cauliflower.setCalorificValue(25);
        cauliflower.setCategory(Product.ProductCategory.VEGETABLE);

        Product assortedVegetables = new Product();
        assortedVegetables.setName("Assorted vegetables");
        assortedVegetables.setCalorificValue(30);
        assortedVegetables.setCategory(Product.ProductCategory.VEGETABLE);

        // PREPARED PRODUCTS:
        ProductDao productDao = AccessConfig.getDaoFactory().getProductDao();
        PreparedProductDao preparedProductDao = AccessConfig.getDaoFactory().getPreparedProductDao();

        PreparedProduct boiledEggs = new PreparedProduct();
        boiledEggs.setProduct(productDao.find(productDao.create(eggs)));
        boiledEggs.setPreparationMethod(PreparedProduct.PreparationMethod.BOILED);
        boiledEggs = preparedProductDao.find(preparedProductDao.create(boiledEggs));

        PreparedProduct rawOliveOil = new PreparedProduct();
        rawOliveOil.setProduct(productDao.find(productDao.create(oliveOil)));
        rawOliveOil.setPreparationMethod(PreparedProduct.PreparationMethod.RAW);
        rawOliveOil = preparedProductDao.find(preparedProductDao.create(rawOliveOil));

        PreparedProduct rawTomato = new PreparedProduct();
        rawTomato.setProduct(productDao.find(productDao.create(tomato)));
        rawTomato.setPreparationMethod(PreparedProduct.PreparationMethod.RAW);
        rawTomato = preparedProductDao.find(preparedProductDao.create(rawTomato));

        PreparedProduct rawLettuce = new PreparedProduct();
        rawLettuce.setProduct(productDao.find(productDao.create(lettuce)));
        rawLettuce.setPreparationMethod(PreparedProduct.PreparationMethod.RAW);
        rawLettuce = preparedProductDao.find(preparedProductDao.create(rawLettuce));

        PreparedProduct stewedCauliflower = new PreparedProduct();
        stewedCauliflower.setProduct(productDao.find(productDao.create(cauliflower)));
        stewedCauliflower.setPreparationMethod(PreparedProduct.PreparationMethod.STEWED);
        stewedCauliflower = preparedProductDao.find(preparedProductDao.create(stewedCauliflower));

        PreparedProduct rawAssortedVegetables = new PreparedProduct();
        rawAssortedVegetables.setProduct(productDao.find(productDao.create(assortedVegetables)));
        rawAssortedVegetables.setPreparationMethod(PreparedProduct.PreparationMethod.RAW);
        rawAssortedVegetables = preparedProductDao.find(preparedProductDao.create(rawAssortedVegetables));

        // RECIPES:
        Recipe egyptianEggSalad = Recipe.builder()
                .setName("Egyptian egg salad")
                .addComponent(boiledEggs, 200)
                .addComponent(rawOliveOil, 20)
                .addComponent(rawTomato, 100)
                .addComponent(rawLettuce, 100)
                .build();

        Recipe springVegetableAndCauliflower = Recipe.builder()
                .setName("Spring vegetable & cauliflower salad")
                .addComponent(stewedCauliflower, 500)
                .addComponent(rawOliveOil, 20)
                .addComponent(rawAssortedVegetables, 200)
                .build();

        recipes.add(egyptianEggSalad);
        recipes.add(springVegetableAndCauliflower);

        RecipeDao recipeDao = AccessConfig.getDaoFactory().getRecipeDao();
        recipes.forEach(recipeDao::create);
    }
}
