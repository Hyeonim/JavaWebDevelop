package com.yi.spring.service;

import com.yi.spring.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenusByRestNo(int restNo);
    Menu createMenu(Menu menu);
    Menu updateMenu(Menu menu);
    void deleteMenu(int menuNo);

    Menu getMenuByMenuNo(int menuNo);
}
