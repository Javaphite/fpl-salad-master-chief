package ua.training.fpl.model.service;

import ua.training.fpl.model.entity.Salad;
import ua.training.fpl.model.entity.SaladRecipe;
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

    public Salad makeSalad(SaladRecipe saladRecipe, int portions) {
        if (!storageService.checkSupplies(saladRecipe, portions)) {
            throw new InsufficientSuppliesException();
        }

        Salad salad = new Salad(saladRecipe, portions);
        storageService.removeSupplies(saladRecipe, portions);

        return salad;
    }

}
