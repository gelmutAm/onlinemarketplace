package com.epam.marketplace.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marketplace")
public class PagesController {

    @GetMapping
    public String getMainPage() {
        return "main-page";
    }

    @GetMapping("/item/{itemId}")
    public String getItemPage(@PathVariable int itemId) {
        return "item-page";
    }

    @GetMapping("/user/items")
    public String getUserItems() {
        return "user-items-page";
    }

    @GetMapping("/user/bids")
    public String getUserBids() {
        return "user-bids-page";
    }
}
