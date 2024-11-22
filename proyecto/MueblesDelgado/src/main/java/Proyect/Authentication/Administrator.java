package Proyect.Authentication;

import jakarta.persistence.*;

import static Proyect.Validations.ValidationUtils.validateNonNull;

@Entity
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name = null;
    private String password = null;

    public Administrator(String p_name, String p_password) {
        setName(p_name);
        setPassword(p_password);
    }

    public Administrator() {

    }

    public String getName() {
        return name;
    }

    public void setName(String p_name) {
        validateNonNull(p_name,"Name");
        this.name = p_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p_password) {
        validateNonNull(p_password,"Password");
        this.password = p_password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}