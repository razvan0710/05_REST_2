package REST_API_2.REST.JPA_Resources;

import REST_API_2.REST.JPA.UserRepository;
import REST_API_2.REST.users.User;
import REST_API_2.REST.users.UserDaoService;
import REST_API_2.REST.users.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {
    private UserDaoService service;
    private UserRepository repository;
    public UserJPAResource(UserDaoService service, UserRepository repository){
        this.service=service;
        this.repository=repository;
    }
    //Get users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }

    //Get users based on id
    @GetMapping("/jpa/users/{id}")
    public Optional<User> retrieveUsers(@PathVariable int id) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if(user==null)
            throw new UserNotFoundException("id"+id);

        return user;
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUsers(@PathVariable int id) throws UserNotFoundException {
        repository.deleteById(id);
    }

    //POST users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        // /users/4 => users/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        // location for user{id}
        return ResponseEntity.created(location).build();
    }


}
