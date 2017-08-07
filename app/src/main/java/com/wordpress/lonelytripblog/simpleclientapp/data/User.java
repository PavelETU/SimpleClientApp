package com.wordpress.lonelytripblog.simpleclientapp.data;


/**
 * User representation for http://jsonplaceholder.typicode.com/users.
 */

public class User {
    private int id;
    private String name;
    private UserAdress address;
    private String email;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address.toString();
    }

    public void setAddress(UserAdress address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private class UserAdress {
        private String street;
        private String city;
        private String zipcode;


        public UserAdress(String street, String city, String zipcode) {
            this.street = street;
            this.city = city;
            this.zipcode = zipcode;
        }

        @Override
        public String toString() {
            return street +
                    ", " + city  +
                    ", " + zipcode;
        }
    }
}
