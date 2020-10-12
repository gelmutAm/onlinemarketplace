package com.epam.marketplace.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <code>PagesController</code> routes pages requests.
 */
@Controller
@RequestMapping("/marketplace")
public class PagesController {

    /**
     * Returns main page of tha application.
     *
     * @return main page of tha application.
     */
    @GetMapping
    public String getMainPage() {
        return "main-page";
    }

    /**
     * Returns page of item with the specified id.
     *
     * @param itemId item id
     * @return page of item with the specified id.
     */
    @GetMapping("/item/{itemId}")
    public String getItemPage(@PathVariable int itemId) {
        return "item-page";
    }

    /**
     * Returns user items page.
     *
     * @return user items page.
     */
    @GetMapping("/user/items")
    public String getUserItems() {
        return "user-items-page";
    }

    /**
     * Returns user bids page.
     *
     * @return user bids page.
     */
    @GetMapping("/user/bids")
    public String getUserBids() {
        return "user-bids-page";
    }
}
