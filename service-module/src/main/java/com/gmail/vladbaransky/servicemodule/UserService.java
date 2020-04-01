package com.gmail.vladbaransky.servicemodule;

import com.gmail.vladbaransky.servicemodule.model.AppUserPrincipal;
import com.gmail.vladbaransky.servicemodule.model.UserDTO;

public interface UserService {

    UserDTO getUserByUsername(String username);

    AppUserPrincipal getCurrentUser();

    String getPageByRole();
}
