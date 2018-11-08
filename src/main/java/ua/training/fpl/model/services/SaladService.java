package ua.training.fpl.model.services;

import ua.training.fpl.model.products.Product;
import ua.training.fpl.model.salads.Salad;
import ua.training.fpl.model.salads.SaladComponent;
import ua.training.fpl.model.salads.SaladRecipe;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SaladService {

    public static final SaladService INSTANCE = new SaladService();

    private SaladService() { }

    public Salad makeSalad(SaladRecipe recipe, int portions) {
        return new Salad(recipe, portions);
    }

    public long getSaladWeight(Salad salad) {
        return  salad.getPortions()*salad.getRecipe().getPortionWeight();
    }

    public long getSaladCalories(Salad salad) {
        return salad.getPortions()*salad.getRecipe().getPortionCalories();
    }

    public Collection<SaladComponent> getSortedComponents(Salad salad, Comparator<SaladComponent> comparator) {
        SortedSet<SaladComponent> components = new TreeSet<>(comparator);
        components.addAll(salad.getRecipe().getComponents());

        return Collections.unmodifiableSortedSet(components);
    }

    public Collection<SaladComponent> filterByTotalCalories(Salad salad, long from, long to) {
        return salad.getRecipe().getComponents().stream()
                .filter(component -> component.getCalories()*salad.getPortions() >= from
                        && component.getCalories()*salad.getPortions() <= to)
                .collect(Collectors.toSet());
    }
}
