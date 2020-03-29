package com.gmail.vladbaransky.servicemodule.impl;

import com.gmail.vladbaransky.repositorymodule.UserRepository;
import com.gmail.vladbaransky.repositorymodule.model.RoleEnum;
import com.gmail.vladbaransky.repositorymodule.model.User;
import com.gmail.vladbaransky.servicemodule.UserService;
import com.gmail.vladbaransky.servicemodule.model.AppUserPrincipal;
import com.gmail.vladbaransky.servicemodule.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
private final static String PAGE_SHOPS="/shops";
private final static String PAGE_ITEMS="/items";
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        UserDTO userDTO = null;
        if (user != null) {
            userDTO = getDTOFromObject(user);
        }
        return userDTO;
    }

    @Override
    public AppUserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (AppUserPrincipal) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    @Override
    public String getPageByRole() {
        String namePage = null;
        if (getCurrentUser() == null) {
            logger.info("Error");
        } else if (getCurrentUser().hasRole(RoleEnum.ROLE_ADMIN)) {
            namePage=PAGE_SHOPS;
        } else{ namePage=PAGE_ITEMS;}
  return namePage;
    }

    private UserDTO getDTOFromObject(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

}
