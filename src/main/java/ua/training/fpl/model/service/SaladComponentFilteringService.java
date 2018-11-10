package ua.training.fpl.model.service;

import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.util.CaloriesCounter;
import ua.training.fpl.util.Numbers;

import java.util.List;
import java.util.stream.Collectors;

public class SaladComponentFilteringService {
    CaloriesCounter caloriesCounter;

    public SaladComponentFilteringService(CaloriesCounter caloriesCounter) {
        this.caloriesCounter = caloriesCounter;
    }

    public List<PreparedProduct> filterByCalories(Salad salad, long from, long to) {
        return salad.getRecipe().getComponents().keySet().stream()
                .filter(product -> Numbers.between(caloriesCounter.caloriesOf(salad, product), from, to))
                .collect(Collectors.toList());
    }

    public CaloriesCounter getCaloriesCounter() {
        return caloriesCounter;
    }

    public void setCaloriesCounter(CaloriesCounter caloriesCounter) {
        this.caloriesCounter = caloriesCounter;
    }
}
