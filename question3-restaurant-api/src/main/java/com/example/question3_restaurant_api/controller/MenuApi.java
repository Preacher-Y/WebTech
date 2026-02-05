package com.example.question3_restaurant_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.question3_restaurant_api.model.MenuItem;


@RestController()
@RequestMapping("/api/menu")
public class MenuApi {

    List<MenuItem> menuItems = new ArrayList<>();


    public MenuApi(){
        menuItems.add(new MenuItem(1, "Garlic Bread", "Toasted baguette with garlic butter", 4.99, "Appetizer", true));
        menuItems.add(new MenuItem(2, "Caesar Salad", "Romaine, parmesan, croutons, Caesar dressing", 7.50, "Appetizer", true));
        menuItems.add(new MenuItem(3, "Margherita Pizza", "Tomato sauce, mozzarella, fresh basil", 12.99, "Main Course", true));
        menuItems.add(new MenuItem(4, "Grilled Salmon", "Salmon fillet with lemon butter sauce", 18.75, "Main Course", false));
        menuItems.add(new MenuItem(5, "Cheeseburger", "Beef patty, cheddar, lettuce, tomato", 10.99, "Main Course", true));
        menuItems.add(new MenuItem(6, "Chocolate Lava Cake", "Warm chocolate cake with molten center", 6.25, "Dessert", true));
        menuItems.add(new MenuItem(7, "Vanilla Ice Cream", "Classic vanilla bean ice cream", 4.50, "Dessert", false));
        menuItems.add(new MenuItem(8, "Iced Coffee", "Cold brewed coffee served over ice", 3.75, "Beverage", true));
    }


    @GetMapping()
    public List<MenuItem> getAllMenuItems() {
        return menuItems;
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable long id) {
        return menuItems.stream()
                    .filter(el->el.getId() == id)
                    .findFirst()
                    .orElseThrow(()->
                        new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Menu Item with Id: "+id+", Not Found"
                        )
                    );
    }
    
    @GetMapping("/category/{category}")
    public List<MenuItem> getMenuItemsByCategory(@PathVariable String category){
        return menuItems.stream()
                    .filter(el->el.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
    }

    @GetMapping("/available")
    public List<MenuItem> getAvailableItems(){
        return menuItems.stream()
                    .filter(el->el.getAvailability() == true)
                    .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<MenuItem> getMenuItemsByName(@RequestParam String name){
        return menuItems.stream()
                    .filter(el->el.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
    }

    @PostMapping()
    public MenuItem addNewMenuItem(@RequestBody MenuItem item){
        menuItems.add(item);
        return item;
    }

    @PutMapping("/{id}/availability")
    public MenuItem changeMenuItemAvailablity(@PathVariable long id){
        MenuItem item = menuItems.stream().filter(el->el.getId() == id)
                            .findFirst()
                            .orElseThrow(()->
                                new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Menut Item with ID: "+id+", Not Found"
                                )
                            );
        item.setAvailability(!item.getAvailability());
        return item;
    }

    @DeleteMapping("/{id}")
    public MenuItem removeItemFromMenu(@PathVariable long id){
        MenuItem item = menuItems.stream().filter(el->el.getId() == id)
                            .findFirst()
                            .orElseThrow(()->
                                new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Menut Item with ID: "+id+", Not Found"
                                )
                            );
        menuItems.remove(item);
        return item;
    }
}
