package io.meli.melishop.db;

import java.util.Map;

import io.meli.melishop.model.User;

public class UserRepo {
    
    public static final User user_1 = new User(1L, OrderRepo.order_1);
    public static final User user_2 = new User(2L, OrderRepo.order_2);
    public static final User user_3 = new User(3L, OrderRepo.order_3);

    public static User getUserById(Long id) {
        switch (id.toString()) {
            case "1":
                return user_1;
            case "2":
                return user_2;
            case "3":
                return user_3;
            default:
                return null;
        }
    }

    public static Map<User, Long> getAllUsers() {
        Map<User, Long> users = Map.of(user_1, 1L, user_2, 2L, user_3, 3L);
        return users;
    }

}
