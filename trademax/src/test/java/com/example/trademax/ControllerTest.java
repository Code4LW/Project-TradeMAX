package com.example.trademax;

import com.example.trademax.controllers.ItemController;
import com.example.trademax.models.Item;
import com.example.trademax.models.User;
import com.example.trademax.services.CustomUserDetailsService;
import com.example.trademax.services.ItemService;
import com.example.trademax.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = FreeMarkerConfigurer.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ItemService itemService;
    @MockBean
    private CustomUserDetailsService userDetailsService;
    @MockBean
    private UserService userService;
    @MockBean
    ItemController itemController;
    @MockBean
    Principal principal;



    //    @Test()
//    public void testMainPage() throws Exception {
//        this.mockMvc.perform(get("/"))
//                .andDo(print())
//                .andExpect(status().isOk());
////                .andExpect(content().string(containsString("Find what you are looking for right here!")));
//
//    }
    @Test
    public void testGetAllItems() throws Exception {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "Item 1", 100, "Description 1", "City 1"));
        items.add(new Item(2L, "Item 2", 200, "Description 2", "City 2"));
        when(itemService.getItems(null)).thenReturn(items);
        User user = userService.getUserByPrincipal(principal);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("item"))
                .andExpect(model().attribute("items", items)).
                andExpect(model().attribute("user", user));

    }
}

