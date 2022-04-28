package Pojo;

public class UbsUser {

    private String id;
    private String name;
    private UbsRole role;
    private String lastName;
    private Integer employeeId;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UbsRole getRole() {
        return role;
    }

    public void setRole(UbsRole role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
