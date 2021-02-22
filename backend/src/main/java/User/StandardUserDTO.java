package User;

public class StandardUserDTO implements UserDTO {

    private final String username;
    private final String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {return false;}


    public StandardUserDTO(String username, String password) {
        this.username = username;
        this.password = password;

    }
}