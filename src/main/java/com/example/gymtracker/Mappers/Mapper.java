package com.example.gymtracker.Mappers;

public interface Mapper <A,B>{

    B mapTo(A a);

    A mapFrom(B b);
}
