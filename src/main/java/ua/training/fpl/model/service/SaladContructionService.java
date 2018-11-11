package ua.training.fpl.model.service;

import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.Recipe;
import ua.training.fpl.model.exception.InsufficientSuppliesException;

public class SaladContructionService {

    private StorageService storageService;

    public SaladContructionService(StorageService storageService) {
        this.storageService = storageService;
    }

    public StorageService getStorageService() {
        return storageService;
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Salad makeSalad(Recipe recipe, int portions) {
        if (!storageService.checkSupplies(recipe, portions)) {
            throw new InsufficientSuppliesException();
        }

        Salad salad = new Salad(recipe, portions);
        storageService.removeSupplies(recipe, portions);

        return salad;
    }

}
