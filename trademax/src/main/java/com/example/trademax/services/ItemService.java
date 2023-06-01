package com.example.trademax.services;

import com.example.trademax.models.Image;
import com.example.trademax.models.Item;
import com.example.trademax.models.User;
import com.example.trademax.repositories.ItemRepository;
import com.example.trademax.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ItemService.class);
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<Item> getItems(String name) {
        if (name != null) return itemRepository.findByName(name);
        else return itemRepository.findAll();
    }

    public void saveItem(Principal principal, Item item, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        item.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1 = toImage(file1);
            image1.setPreview(true);
            item.addImage(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImage(file2);
            item.addImage(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImage(file3);
            item.addImage(image3);
        }
       log.info("saving new item. Name: {}, price: {}, email: {})", item.getName(), item.getPrice(), item.getUser().getEmail()) ;
        Item itemFromDb = itemRepository.save(item);
        itemFromDb.setPreviewImageId(itemFromDb.getImages().get(0).getId());
       itemRepository.save(item);
    }
    public User getUserByPrincipal(Principal principal){
        if(principal == null) return new User();
        return  userRepository.findByEmail(principal.getName());
    }
    private Image toImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setFileName(file.getOriginalFilename()) ;
        image.setContentType(file.getContentType()) ;
        image.setSize(file.getSize()) ;
        image.setBytes(file.getBytes());
        return image;
    }
    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

}
