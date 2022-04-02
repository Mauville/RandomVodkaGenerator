package com.example.randomvodkagenerator.rngs;

public interface CheckableGenerator extends Generator{
    public boolean validate(CheckTypes c);
}
