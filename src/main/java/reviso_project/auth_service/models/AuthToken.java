package reviso_project.auth_service.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Auth-Tokens")
public class AuthToken {
    @Id
    private String userName;
    @Column(nullable = false)
    private String refreshToken;
    @Column(nullable = false)
    private String accessToken;
}
