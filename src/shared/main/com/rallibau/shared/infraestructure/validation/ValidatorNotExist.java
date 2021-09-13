package com.rallibau.shared.infraestructure.validation;

public final class ValidatorNotExist extends Exception {
    public ValidatorNotExist(String name) {
        super(String.format("The validator <%s> does not exist", name));
    }
}
