package REST_API_2.REST.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static {
        users.add(new User(++userCount,"Adam", LocalDate.now()));
        users.add(new User(++userCount,"Avdfbfgbgthm", LocalDate.now()));
        users.add(new User(++userCount,"bgbfgbm", LocalDate.now()));
    }

    public List<User> findAll(){
        return users;
    }
    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteUser(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }


}
