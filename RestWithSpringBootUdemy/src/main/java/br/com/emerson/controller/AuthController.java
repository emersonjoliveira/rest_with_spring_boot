package br.com.emerson.controller;

import br.com.emerson.repository.UserRepository;
import br.com.emerson.security.AccountCredentialsVO;
import br.com.emerson.security.jwt.JWTTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@Api(tags = "AuthenticationEndpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @ApiOperation(value = "Authenticate a user by your credentials")
    @PostMapping(value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity signin(@RequestBody AccountCredentialsVO credentialsVO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentialsVO.getUsername(), credentialsVO.getPassword()));

            var user = repository.findByUserName(credentialsVO.getUsername());

            if (Objects.isNull(user)) {
                throw new UsernameNotFoundException(
                        String.format("Username %s not found!", credentialsVO.getUsername()));
            }
            Map<String, Object> model = new HashMap<>();
            model.put("username", credentialsVO.getUsername());
            model.put("token", tokenProvider.createToken(credentialsVO.getUsername(), user.getRoles()));

            return ok(model);
        } catch (AuthenticationException e ) {
            throw new BadCredentialsException("Invalid username/password!");
        }
    }
}
