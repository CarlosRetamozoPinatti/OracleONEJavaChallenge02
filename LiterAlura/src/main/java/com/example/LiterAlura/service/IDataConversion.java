package com.example.LiterAlura.service;

public interface IDataConversion {
    <T> T getData(String json, Class<T> tClass);
}
