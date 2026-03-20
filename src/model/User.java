package model;

public class User {

    String name;
    String role;
    int age;
    boolean isMember;
    long phone_number;
    String address;

    public User(String name, String role, int age, boolean isMember, long phone_number, String address) {
        this.name = name;
        this.role = role;
        this.age = age;
        this.isMember = isMember;
        this.phone_number = phone_number;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void setPhone(long phone_number) {
        this.phone_number = phone_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public int getAge() {
        return age;
    }

    public boolean isMember() {
        return isMember;
    }

    public long getPhone() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

}
