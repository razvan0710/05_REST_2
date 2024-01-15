package REST_API_2.REST.JPA;

import REST_API_2.REST.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
