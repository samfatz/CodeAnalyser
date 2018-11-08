package Abusers.ACwDI;

public class Underling {

    private Long id;
    private String firstName;
    private String lastName;
    private Manager lineManager;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setManager(Manager boss){
        lineManager = boss;
    }

    public Manager getManager(){
        return lineManager;
    }
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", manager=" + lineManager + "]";
    }
}