package com.example.randomvodkagenerator.rngs;

public interface CheckableNumberGenerator extends NumberGenerator {
    public boolean validate(CheckTypes c);
}
