/**
 * @author Suleyman Golbol
 * Abstract user class that contains basic info of an user.
 */
public abstract class Users implements UsersInterface{
    protected String name;
    protected String surname;
    protected String email;
    private String password;

    /**
     * 
     * @param name Name of user
     * @param surname Surname of user
     * @param email email of user
     * @param password password of user
     */
    public Users(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    
    
    
}
