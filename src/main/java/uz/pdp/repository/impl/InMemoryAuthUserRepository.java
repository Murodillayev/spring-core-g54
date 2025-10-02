package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.enums.AuthRole;
import uz.pdp.repository.AuthUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//
//public class InMemoryAuthUserRepository implements AuthUserRepository {
//
//
//
//    @Override
//    public AuthUser save(AuthUser authUser) {
//        users.add(authUser);
//        return authUser;
//    }
//
//    @Override
//    public Optional<AuthUser> findById(String id) {
//        return users.stream()
//                .filter(u -> u.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<AuthUser> findAll() {
//        return users;
//    }
//
//    @Override
//    public void delete(AuthUser authUser) {
//        users.removeIf(u -> u.getId().equals(authUser.getId()));
//    }
//}
