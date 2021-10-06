package com.rallibau.acl.user.domain;


public final class UserMother {

    public static User create(UserId id, UserName userName, UserPassword userPassword){
        return User.create(id,userName,userPassword);
    }

    public static User random(){
        return User.create(UserIdMother.random(),UserNameMother.random(),UserPasswordMother.random());
    }
}
