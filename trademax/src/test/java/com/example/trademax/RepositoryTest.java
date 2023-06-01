package com.example.trademax;
import com.example.trademax.models.Image;
import com.example.trademax.models.Item;
import com.example.trademax.models.User;
import com.example.trademax.models.enums.Role;
import com.example.trademax.repositories.ImageRepository;
import com.example.trademax.repositories.ItemRepository;
import com.example.trademax.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest()

public class RepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Test
    public void testSaveItem() {
        Item item = new Item(null, "Test Item", 100, "Test Description", "Test City");
        Item savedItem = itemRepository.save(item);

        assertNotNull(savedItem.getId());
        assertEquals(itemRepository.findById(1L).orElse(null).getName(), "Test Item");
        assertEquals("Test Item", savedItem.getName());
        assertEquals(100, savedItem.getPrice());
        assertEquals("Test Description", savedItem.getDescription());
        assertEquals("Test City", savedItem.getCity());
    }

    @Test
    public void testFindByName() {
        Item item1 = new Item(1L, "Item 1", 100, "Description 1", "City 1");
        Item item2 = new Item(2L, "Item 2", 200, "Description 2", "City 2");
        Item item3 = new Item(3L, "Item 3", 300, "Description 3", "City 3");

        itemRepository.saveAll(List.of(item1, item2, item3));

        List<Item> items = itemRepository.findByName("Item 2");

        assertEquals(1, items.size());
        Item randomItem = items.get(0);
        assertEquals("Item 2", randomItem.getName());
        assertEquals(200, randomItem.getPrice());
        assertEquals("Description 2", randomItem.getDescription());
        assertEquals("City 2", randomItem.getCity());
    }
    @Test
    public void testDeleteItem() {
        Item item = new Item();
        item.setName("Test Item");
        item.setPrice(100);
        item.setDescription("Test Description");
        item.setCity("Test City");
        itemRepository.save(item);
        itemRepository.deleteById(item.getId());
        Optional<Item> deletedItem = itemRepository.findById(item.getId());
        assertFalse(deletedItem.isPresent());
    }
    @Test
    public void testUserFindByEmail() {

        User user = new User();
        user.setName("Random User");
        user.setEmail("random.user@gmail.com");
        user.setPhoneNumber("123456789");
        user.setActive(true);
        user.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);

        User foundUser = userRepository.findByEmail("random.user@gmail.com");

        assertNotNull(foundUser);
        assertEquals("Random User", foundUser.getName());
    }
    @Test
    public void deleteUserById(){
        User user = new User();
        user.setName("Random User");
        user.setEmail("random.user@gmail.com");
        user.setPhoneNumber("123456789");
        user.setActive(true);
        user.setPassword(passwordEncoder.encode("password"));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
        Long id = user.getId();
        userRepository.deleteById(id);
        User deletedUser = userRepository.findById(id).orElse(null);
        assertNull(deletedUser);

    }
    @Test
    public void testSaveImage() {
        // Create a new image
        Image image = new Image();
        image.setName("Image");
        image.setFileName("image.jpg");
        image.setSize(1024L);
        image.setContentType("image/jpeg");
        image.setPreview(true);

        Image savedImage = imageRepository.save(image);

        assertNotNull(savedImage.getId());
    }

    @Test
    public void testDeleteImage() {
        // Create a new image
        Image image = new Image();
        image.setName("Image");
        image.setFileName("image.jpg");
        image.setSize(1024L);
        image.setContentType("image/jpeg");
        image.setPreview(true);
        imageRepository.save(image);


        Long imageId = image.getId();


        imageRepository.deleteById(imageId);

        Image deletedImage = imageRepository.findById(imageId).orElse(null);

        assertNull(deletedImage);
    }
}




