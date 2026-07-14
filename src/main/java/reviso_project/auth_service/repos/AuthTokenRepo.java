package reviso_project.auth_service.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reviso_project.auth_service.models.AuthToken;

@Repository
public interface AuthTokenRepo extends JpaRepository<AuthToken,String > {
}
