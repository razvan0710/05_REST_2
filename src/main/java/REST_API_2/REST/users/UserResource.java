package REST_API_2.REST.users;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

import java.util.List;

@RestController
public class UserResource {
    private UserDaoService service;
    public UserResource(UserDaoService service){
        this.service=service;
    }
    //Get users
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //Get users based on id
    @GetMapping("/users/{id}")
    public User retrieveUsers(@PathVariable int id) throws UserNotFoundException {
        User user = service.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id"+id);

        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id) throws UserNotFoundException {
        service.deleteUser(id);
    }

    //POST users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        // /users/4 => users/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        // location for user{id}
        return ResponseEntity.created(location).build();
    }


}
