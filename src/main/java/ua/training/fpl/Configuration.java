package ua.training.fpl;

import ua.training.fpl.command.CreateSalad;
import ua.training.fpl.command.DisplayKnownRecipes;
import ua.training.fpl.command.DisplayProductsInCaloriesBounds;
import ua.training.fpl.command.DisplaySaladDetails;
import ua.training.fpl.command.DisplayVeganRecipes;
import ua.training.fpl.command.HttpServletCommand;
import ua.training.fpl.command.SortProductsByCalories;
import ua.training.fpl.command.SortRecipesByCalories;
import ua.training.fpl.model.service.RecipeService;
import ua.training.fpl.model.service.SaladService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Configuration {

    private static final Configuration INSTANCE = new Configuration();

    // Resource access constants
    private String pagePrefix = "/WEB-INF/";
    private String pageSuffix = ".jsp";
    private String indexPage = "recipes";
    private String saladPage = "salad";

    // Command-URI mappings
    private Map<String, Class<? extends HttpServletCommand>> commandMapping;
    {
        commandMapping = new HashMap<>();
        commandMapping.put("displayKnownRecipes", DisplayKnownRecipes.class);
        commandMapping.put("displayVeganRecipes", DisplayVeganRecipes.class);
        commandMapping.put("sortRecipesByCalories", SortRecipesByCalories.class);
        commandMapping.put("createSalad", CreateSalad.class);
        commandMapping.put("displayProductsInCaloriesBounds", DisplayProductsInCaloriesBounds.class);
        commandMapping.put("displaySaladDetails", DisplaySaladDetails.class);
        commandMapping.put("sortProductsByCalories", SortProductsByCalories.class);
        commandMapping = Collections.unmodifiableMap(commandMapping);
    }

    // JSP params
    private String recipesParam = "recipes";
    private String recipeIdentifierParam = "recipe";
    private String orderingParam = "descending";
    private String saladDetailsRedirectTemplate = "./?action=displaySaladDetails&id=";


    private RecipeService recipeService = new RecipeService();
    private SaladService saladService = new SaladService();

    private Configuration() {}

    public static Configuration getInstance() {
        return INSTANCE;
    }

    private static String getPageResource(String page) {
        return INSTANCE.pagePrefix + page + INSTANCE.pageSuffix;
    }

    public static RecipeService getRecipeService() {
        return INSTANCE.recipeService;
    }

    public static SaladService getSaladService() {
        return INSTANCE.saladService;
    }

    public static String getIndexPage() {
        return getPageResource(INSTANCE.indexPage);
    }

    public static String getSaladPage() {
        return getPageResource(INSTANCE.saladPage);
    }

    public static String getRecipesParam() {
        return INSTANCE.recipesParam;
    }

    public static String getOrderingParam() {
        return INSTANCE.orderingParam;
    }

    public static String getRecipeIdentifierParam() {
        return INSTANCE.recipeIdentifierParam;
    }

    public static Map<String, Class<? extends HttpServletCommand>> getCommandMapping() {
        return INSTANCE.commandMapping;
    }

    public static String getSaladRedirectionLink(int id) {
        return INSTANCE.saladDetailsRedirectTemplate + id;
    }
}
