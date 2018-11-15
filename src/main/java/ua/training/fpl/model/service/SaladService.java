package ua.training.fpl.model.service;

import ua.training.fpl.config.AccessConfig;
import ua.training.fpl.model.dao.SaladDao;
import ua.training.fpl.model.dto.SaladComponent;
import ua.training.fpl.model.dto.SaladSummary;
import ua.training.fpl.model.entity.PreparedProduct;
import ua.training.fpl.model.entity.Product;
import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.util.CaloriesCounter;
import ua.training.fpl.util.VeganDetector;
import ua.training.fpl.util.WeightCounter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Includes service methods to work with salad.
 */
public class SaladService {

    /**
     * Creates new salad in remote storage of given recipe and number of portions.
     * @param recipe
     * @param portions
     * @return instance of created {@link Salad}
     */
    public Salad createSalad(Recipe recipe, int portions) {
        Salad salad = new Salad(recipe, portions);
        SaladDao saladDao = AccessConfig.getDaoFactory().getSaladDao();
        salad.setId(saladDao.create(salad));
        return salad;
    }

    /**
     * Retrieves salad from remote storage by id.
     * @param id
     * @return instance of retrieved salad
     */
    public Salad getSaladById(int id) {
        return AccessConfig.getDaoFactory().getSaladDao().find(id);
    }

    /**
     * Sorts components in salad using given {@link Comparator}.
     * @param salad
     * @param comparator
     * @return list of salad components details
     */
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

    public SaladSummary getSaladSummary(Salad salad) {
        return new SaladSummary()
                .setName(salad.getRecipe().getName())
                .setPortions(salad.getPortions())
                .setTotalWeight(WeightCounter.weightOf(salad))
                .setTotalCalories(CaloriesCounter.caloriesOf(salad))
                .setVegan(VeganDetector.isVegan(salad));
    }
}
