public interface UsersInterface {
    /**
     * @return name of User
     */
    public String getName();
    /**
     * @param name name of User will be set
     */
    public void setName(String name);

    /**
     * 
     * @return surname of user
     */
    public String getSurname();

    /**
     * 
     * @param surname sets surname of user
     */
    public void setSurname(String surname);

    /**
     * @return email of user
     */
    public String getEmail();

    /**
     * 
     * @param email sets email for user.
     */
    public void setEmail(String email);

    /**
     * Getting password of user for mathcing. Password won't be shared.
     * @return
     */
    public String getPassword();

    /**
     * Set password to user
     * @param password password of admin
     */
    public void setPassword(String password);

}
