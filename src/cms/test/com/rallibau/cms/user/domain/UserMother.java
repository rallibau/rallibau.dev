package com.rallibau.cms.user.domain;


public final class UserMother {

    public static User create(UserId id, UserName userName,PageCount pageCount){
        return User.create(id,userName,pageCount);
    }

    public static User random(){
        return User.create(UserIdMother.random(), UserNameMother.random(),PageCountMother.random());
    }
}
