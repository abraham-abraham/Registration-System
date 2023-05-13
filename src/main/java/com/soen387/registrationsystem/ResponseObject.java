package com.soen387.registrationsystem;

class ResponseObject<T> {
    public T data;
    public String error;
    public ResponseObject(T data, String error) {
        this.data = data;
        this.error = error;
    }
}