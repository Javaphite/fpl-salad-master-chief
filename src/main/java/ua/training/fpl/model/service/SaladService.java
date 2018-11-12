package ua.training.fpl.model.service;

import ua.training.fpl.dto.SaladComponent;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Product;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.util.CaloriesCounter;
import ua.training.fpl.util.VeganDetector;
import ua.training.fpl.util.WeightCounter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SaladService {

    private Map<Integer, Salad> salads = new HashMap<>();
    static int nextId=1;

    public Salad createSalad(Recipe recipe, int portions) {
        int id = nextId++;
        Salad salad = new Salad(recipe, portions);
        salad.setId(id);
        salads.put(id, salad);
        return salad;
    }

    public Salad getSaladById(int id) {
        return salads.get(id);
    }

    public List<SaladComponent> getComponentsSorted(Salad salad, Comparator<SaladComponent> comparator) {
        List<SaladComponent> saladComponents = getComponentsOf(salad);
        saladComponents.sort(comparator);
        return saladComponents;
    }

    public List<SaladComponent> getComponentsFiltered(Salad salad, Predicate<SaladComponent> predicate) {
        List<SaladComponent> saladComponents = getComponentsOf(salad);
        saladComponents.removeIf(predicate.negate());
        return saladComponents;
    }

    public List<SaladComponent> getComponentsOf(Salad salad) {
        return salad.getRecipe().getProducts().keySet().stream()
                .map(product -> getSaladComponent(salad, product))
                .collect(Collectors.toList());
    }

    private SaladComponent getSaladComponent(Salad salad, PreparedProduct preparedProduct) {
        Product product = preparedProduct.getProduct();
        return new SaladComponent()
                .setName(product.getName())
                .setPreparationMethod(preparedProduct.getPreparationMethod().name())
                .setCategory(product.getCategory().name())
                .setCalorificValue(product.getCalorificValue())
                .setWeight(WeightCounter.weightOf(salad, preparedProduct))
                .setCalories(CaloriesCounter.caloriesOf(salad, preparedProduct))
                .setVegan(VeganDetector.isVegan(product));
    }
}