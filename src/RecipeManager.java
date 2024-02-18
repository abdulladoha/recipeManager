import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Recipe {
    private String name;
    private List<String> ingredients;

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Recipe: " + name + "\nIngredients: " + ingredients;
    }
}

public class RecipeManager {
    private List<Recipe> recipes;

    public RecipeManager() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void viewRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            for (Recipe recipe : recipes) {
                System.out.println(recipe);
            }
        }
    }

    public List<Recipe> searchRecipes(String keyword) {
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }

    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nRecipe Manager");
            System.out.println("1. Add Recipe");
            System.out.println("2. View Recipes");
            System.out.println("3. Search Recipes");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter recipe name: ");
                    String recipeName = scanner.nextLine();
                    Recipe recipe = new Recipe(recipeName);

                    System.out.println("Enter ingredients (one per line, type 'done' to finish):");
                    while (true) {
                        String ingredient = scanner.nextLine();
                        if (ingredient.equalsIgnoreCase("done")) {
                            break;
                        }
                        recipe.addIngredient(ingredient);
                    }

                    manager.addRecipe(recipe);
                    System.out.println("Recipe added successfully!");
                    break;

                case 2:
                    System.out.println("\nAll Recipes:");
                    manager.viewRecipes();
                    break;

                case 3:
                    System.out.print("Enter search keyword: ");
                    String keyword = scanner.nextLine();
                    List<Recipe> searchResults = manager.searchRecipes(keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No recipes found matching the keyword.");
                    } else {
                        System.out.println("\nSearch Results:");
                        for (Recipe result : searchResults) {
                            System.out.println(result);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
