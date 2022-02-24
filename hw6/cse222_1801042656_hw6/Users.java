package cse222_1801042656_hw6;
/**
 * @author Suleyman Golbol
 * Abstract user class that contains basic info of an user.
 */
public abstract class Users implements UsersInterface{
    protected String name;
    protected String surname;
    protected long userID;
    private String password;

    /**
     * 
     * @param name Name of user
     * @param surname Surname of user
     * @param userID email of user
     * @param password password of user
     */
    public Users(String name, String surname, long userID, String password) {
        this.name = name;
        this.surname = surname;
        this.userID = userID;
        this.password = password;
    }

    public Users(String name, long userID, String password){
        this.userID = userID;
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
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public long getUserID(){
        return userID;
    }

    public void setUserID(long userID){
        this.userID = userID;
    }

    
    
    
}
