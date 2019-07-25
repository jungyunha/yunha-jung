package com.revature.bean;

public class Employee {

    private int id;
    private String name;
    private int manager_id;
    private String username;
    private String password;
    private String email;
    private int employeetype;

    public Employee(int id, String name, int manager_id, String username, String password, int employeetype, String email) {
        super();
        this.id = id;
        this.name = name;
        this.manager_id = manager_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.employeetype = employeetype;
    }

    public Employee(String name, int manager_id, String username, String password, int employeetype, String email) {
        super();
        this.name = name;
        this.manager_id = manager_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.employeetype = employeetype;
    }
    public Employee(int id, String firstname, String lastname, String email){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Employee() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public int getEmployeetype() {

        return employeetype;
    }

    public void setEmployeetype(int employeetype) {

        this.employeetype = employeetype;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public int getManager_id() {

        return manager_id;
    }

    public void setManager_id(int manager_id) {

        this.manager_id = manager_id;
    }
}

	
	
