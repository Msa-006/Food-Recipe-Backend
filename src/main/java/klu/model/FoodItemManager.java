package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.repository.FoodItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemManager {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public String addFoodItem(FoodItem foodItem) {
        try {
            if (foodItem.getName() == null || foodItem.getName().trim().isEmpty()) {
                return "400::Food item name is required";
            }

            foodItemRepository.save(foodItem);
            return "200::Food item added successfully";
        } catch (Exception e) {
            return "500::Error adding food item: " + e.getMessage();
        }
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> getFoodItemById(Long id) {
        return foodItemRepository.findById(id);
    }

    public List<FoodItem> getFoodItemsByCategory(String category) {
        return foodItemRepository.findByCategory(category);
    }

    public List<FoodItem> getFoodItemsByCuisine(String cuisine) {
        return foodItemRepository.findByCuisine(cuisine);
    }

    public List<FoodItem> getFoodItemsByDifficulty(String difficulty) {
        return foodItemRepository.findByDifficulty(difficulty);
    }

    public List<FoodItem> getFoodItemsByCreator(String email) {
        return foodItemRepository.findByCreatedBy(email);
    }

    public List<FoodItem> searchFoodItems(String keyword) {
        return foodItemRepository.searchByKeyword(keyword);
    }

    public List<String> getAllCategories() {
        return foodItemRepository.findAllCategories();
    }

    public List<String> getAllCuisines() {
        return foodItemRepository.findAllCuisines();
    }

    public List<FoodItem> getTopRatedFoodItems() {
        return foodItemRepository.findTopRated();
    }

    public String updateFoodItem(Long id, FoodItem updatedFoodItem) {
        try {
            Optional<FoodItem> existingItem = foodItemRepository.findById(id);

            if (existingItem.isEmpty()) {
                return "404::Food item not found";
            }

            FoodItem foodItem = existingItem.get();

            if (updatedFoodItem.getName() != null) foodItem.setName(updatedFoodItem.getName());
            if (updatedFoodItem.getDescription() != null) foodItem.setDescription(updatedFoodItem.getDescription());
            if (updatedFoodItem.getCategory() != null) foodItem.setCategory(updatedFoodItem.getCategory());
            if (updatedFoodItem.getCuisine() != null) foodItem.setCuisine(updatedFoodItem.getCuisine());
            if (updatedFoodItem.getIngredients() != null) foodItem.setIngredients(updatedFoodItem.getIngredients());
            if (updatedFoodItem.getInstructions() != null) foodItem.setInstructions(updatedFoodItem.getInstructions());
            if (updatedFoodItem.getPrepTime() != null) foodItem.setPrepTime(updatedFoodItem.getPrepTime());
            if (updatedFoodItem.getCookTime() != null) foodItem.setCookTime(updatedFoodItem.getCookTime());
            if (updatedFoodItem.getServings() != null) foodItem.setServings(updatedFoodItem.getServings());
            if (updatedFoodItem.getDifficulty() != null) foodItem.setDifficulty(updatedFoodItem.getDifficulty());
            if (updatedFoodItem.getImageUrl() != null) foodItem.setImageUrl(updatedFoodItem.getImageUrl());
            if (updatedFoodItem.getCalories() != null) foodItem.setCalories(updatedFoodItem.getCalories());
            if (updatedFoodItem.getRating() != null) foodItem.setRating(updatedFoodItem.getRating());

            foodItemRepository.save(foodItem);
            return "200::Food item updated successfully";
        } catch (Exception e) {
            return "500::Error updating food item: " + e.getMessage();
        }
    }

    public String deleteFoodItem(Long id) {
        try {
            if (!foodItemRepository.existsById(id)) {
                return "404::Food item not found";
            }

            foodItemRepository.deleteById(id);
            return "200::Food item deleted successfully";
        } catch (Exception e) {
            return "500::Error deleting food item: " + e.getMessage();
        }
    }
}
