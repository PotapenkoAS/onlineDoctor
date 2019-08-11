package com.vlsu.demo.service;

import org.springframework.stereotype.Service;

@Service
public class RestService {

    public String convertArrayStringToString(String array) {
        return array.substring(1, array.length() - 1);
    }
}
