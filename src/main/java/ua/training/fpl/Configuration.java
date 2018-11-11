package ua.training.fpl;

import ua.training.fpl.command.DisplayKnownRecipes;
import ua.training.fpl.command.DisplayVeganRecipes;
import ua.training.fpl.command.HttpServletCommand;
import ua.training.fpl.model.service.RecipeService;
import ua.training.fpl.model.service.StorageService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class Configuration {

    private static final Configuration INSTANCE = new Configuration();

    // Resource access constants
    private String pagePrefix = "/WEB-INF/";
    private String pageSuffix = ".jsp";
    private String indexPageName = "welcome";

    // Command-URI mappings
    private Map<String, Class<? extends HttpServletCommand>> commandMapping;
    {
        commandMapping = new HashMap<>();
        commandMapping.put("displayKnownRecipes", DisplayKnownRecipes.class);
        commandMapping.put("displayVeganRecipes", DisplayVeganRecipes.class);
        commandMapping = Collections.unmodifiableMap(commandMapping);
    }

    // JSP params
    private String recipesParam = "recipes";
    private String orderingParam = "descending";

    private RecipeService recipeService = new RecipeService();
    private StorageService storageService = new StorageService();

    private Configuration() {}

    public static Configuration getInstance() {
        return INSTANCE;
    }

    public static String getPageResource(String page) {
        return INSTANCE.pagePrefix + page + INSTANCE.pageSuffix;
    }

    public static RecipeService getRecipeService() {
        return INSTANCE.recipeService;
    }

    public static StorageService getStorageService() {
        return INSTANCE.storageService;
    }

    public static String getIndexPage() {
        return getPageResource(INSTANCE.indexPageName);
    }

    public static String getRecipesParam() {
        return INSTANCE.recipesParam;
    }

    public static String getOrderingParam() {
        return INSTANCE.orderingParam;
    }

    public static Map<String, Class<? extends HttpServletCommand>> getCommandMapping() {
        return INSTANCE.commandMapping;
    }
}
