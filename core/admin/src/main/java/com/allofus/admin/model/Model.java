package com.allofus.admin.model;

public interface Model {

    Long getId();
    default void update(Model model){

    }
}
