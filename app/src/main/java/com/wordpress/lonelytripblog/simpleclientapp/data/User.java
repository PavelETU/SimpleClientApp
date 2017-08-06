package com.wordpress.lonelytripblog.simpleclientapp.data;

/**
 * User representation for http://jsonplaceholder.typicode.com/users.
 */

public class User {
    private int id;
    private String name;
    private UserAdress address;

    private class UserAdress {
        private String street;
        private String city;
        private String zipcode;


        public UserAdress(String street, String city, String zipcode) {
            this.street = street;
            this.city = city;
            this.zipcode = zipcode;
        }
    }
}
