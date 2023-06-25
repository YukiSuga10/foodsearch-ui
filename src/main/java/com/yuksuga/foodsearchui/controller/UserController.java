package com.yuksuga.foodsearchui.controller;

import com.yuksuga.foodsearchui.dao.UserDao;
import com.yuksuga.foodsearchui.domain.User;
import com.yuksuga.foodsearchui.domain.UserList;
import com.yuksuga.foodsearchui.helper.MessageSourceHelper;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final MessageSourceHelper messageSourceHelper;

    public UserController(UserDao userDao, PasswordEncoder passwordEncoder, MessageSourceHelper messageSourceHelper) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.messageSourceHelper = messageSourceHelper;
    }

    @GetMapping(path = "/list")
    public String list(Model model){
        UserList userList = this.userDao.find(null);
        List<User> users = userList.getUsers();

        model.addAttribute("users", users);

        if (users.isEmpty()){
            model.addAttribute("nodata", messageSourceHelper.getMessage(""));
        }

        return "user/list";
    }
}
