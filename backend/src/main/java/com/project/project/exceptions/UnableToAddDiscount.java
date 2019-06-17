package com.project.project.exceptions;

public class UnableToAddDiscount extends Exception {
    public UnableToAddDiscount() { super("Discount is eighter with overlaping date range or with non existing room");}
}
