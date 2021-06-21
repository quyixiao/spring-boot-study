package com.example.springbootstudy.service;


public class ScopedProxyModeB  implements IScopedProxyMode{



    @Override
    public IScopedProxyMode getProxyModelTest() {
        return this;
    }

    @Override
    public Object getTargetObject() {
        return null;
    }

    @Override
    public void removeFromScope() {

    }
}
