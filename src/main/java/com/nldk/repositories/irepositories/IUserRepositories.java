package com.nldk.repositories.irepositories;

import com.nldk.pojo.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepositories {
    User getUserByEmail(String email);
    User getUserByEmailAndPassword(String email, String password);
}
