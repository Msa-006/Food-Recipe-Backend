package klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import klu.model.FoodItem;
import klu.model.FoodItemManager;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fooditems")
@CrossOrigin(origins = "*")
public class FoodItemController {

    @Autowired
    private FoodItemManager foodItemManager;

    @GetMapping
    public ResponseEntity<?> getAllFoodItems() {
        try {
            List<FoodItem> foodItems = foodItemManager.getAllFoodItems();
            return ResponseEntity.ok(foodItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving food items: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFoodItemById(@PathVariable Long id) {
        try {
            Optional<FoodItem> foodItem = foodItemManager.getFoodItemById(id);
            if (foodItem.isPresent()) {
                return ResponseEntity.ok(foodItem.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Food item not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving food item: " + e.getMessage());
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getFoodItemsByCategory(@PathVariable String category) {
        try {
            List<FoodItem> foodItems = foodItemManager.getFoodItemsByCategory(category);
            return ResponseEntity.ok(foodItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving food items by category: " + e.getMessage());
        }
    }

    @GetMapping("/cuisine/{cuisine}")
    public ResponseEntity<?> getFoodItemsByCuisine(@PathVariable String cuisine) {
        try {
            List<FoodItem> foodItems = foodItemManager.getFoodItemsByCuisine(cuisine);
            return ResponseEntity.ok(foodItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving food items by cuisine: " + e.getMessage());
        }
    }

    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<?> getFoodItemsByDifficulty(@PathVariable String difficulty) {
        try {
            List<FoodItem> foodItems = foodItemManager.getFoodItemsByDifficulty(difficulty);
            return ResponseEntity.ok(foodItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving food items by difficulty: " + e.getMessage());
        }
    }

    @GetMapping("/creator/{email}")
    public ResponseEntity<?> getFoodItemsByCreator(@PathVariable String email) {
        try {
            List<FoodItem> foodItems = foodItemManager.getFoodItemsByCreator(email);
            return ResponseEntity.ok(foodItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving food items by creator: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFoodItems(@RequestParam String keyword) {
        try {
            List<FoodItem> foodItems = foodItemManager.searchFoodItems(keyword);
            return ResponseEntity.ok(foodItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error searching food items: " + e.getMessage());
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        try {
            List<String> categories = foodItemManager.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving categories: " + e.getMessage());
        }
    }

    @GetMapping("/cuisines")
    public ResponseEntity<?> getAllCuisines() {
        try {
            List<String> cuisines = foodItemManager.getAllCuisines();
            return ResponseEntity.ok(cuisines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving cuisines: " + e.getMessage());
        }
    }

    @GetMapping("/top-rated")
    public ResponseEntity<?> getTopRatedFoodItems() {
        try {
            List<FoodItem> foodItems = foodItemManager.getTopRatedFoodItems();
            return ResponseEntity.ok(foodItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving top rated food items: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addFoodItem(@RequestBody FoodItem foodItem) {
        String result = foodItemManager.addFoodItem(foodItem);
        String[] parts = result.split("::", 2);
        int statusCode = Integer.parseInt(parts[0]);
        String message = parts[1];

        if (statusCode == 200) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } else {
            return ResponseEntity.status(statusCode).body(message);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFoodItem(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        String result = foodItemManager.updateFoodItem(id, foodItem);
        String[] parts = result.split("::", 2);
        int statusCode = Integer.parseInt(parts[0]);
        String message = parts[1];

        return ResponseEntity.status(statusCode).body(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable Long id) {
        String result = foodItemManager.deleteFoodItem(id);
        String[] parts = result.split("::", 2);
        int statusCode = Integer.parseInt(parts[0]);
        String message = parts[1];

        return ResponseEntity.status(statusCode).body(message);
    }
}
