package com.rallibau.shared.domain.authentication;

import com.rallibau.shared.domain.AggregateRoot;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface HasPermission {
    Permission value();
    Class aggregate();
}
