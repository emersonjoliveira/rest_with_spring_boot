package br.com.emerson.services;

import br.com.emerson.repository.UserRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        var user = repository.findByUserName(userName);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException(String.format("User name %s not found", userName));
    }
}
