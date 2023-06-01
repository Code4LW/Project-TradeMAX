package com.example.trademax.controllers;

import com.example.trademax.models.Item;
import com.example.trademax.models.User;
import com.example.trademax.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class ItemController {
    private final ItemService itemService;
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    @GetMapping("/")
    public String getAllItems(@RequestParam(name = "name", required = false) String name, Principal principal, Model model){
        model.addAttribute("items", itemService.getItems(name));
        model.addAttribute("user", itemService.getUserByPrincipal(principal));
        model.addAttribute("name", name);
        return "item";
    }
    @GetMapping("/item/{id}")
    public String getItemInfo(@PathVariable Long id, Model model, Principal principal){
        Item item = itemService.findById(id);
        model.addAttribute("user", itemService.getUserByPrincipal(principal));
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("images", item.getImages());
        return "item-info";
    }
   @PostMapping("/item/post")
    public String postItem(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                           @RequestParam("file3") MultipartFile file3, Item item, Principal principal) throws IOException{
        itemService.saveItem(principal,item, file1,file2,file3);
        return "redirect:/";
   }
    @GetMapping("/my/products")
    public String userProducts(Principal principal, Model model) {
        User user = itemService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("items", user.getItems());
        return "my-items";
    }
   @PostMapping("/item/delete/{id}")
   public String deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return "redirect:/my/products";

   }

}
