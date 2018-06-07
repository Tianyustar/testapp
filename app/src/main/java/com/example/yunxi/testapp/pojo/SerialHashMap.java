package com.example.yunxi.testapp.pojo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SerialHashMap extends HashMap implements Serializable {

    public SerialHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public SerialHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    public SerialHashMap() {
    }

    public SerialHashMap(Map m) {
        super(m);
    }
}
