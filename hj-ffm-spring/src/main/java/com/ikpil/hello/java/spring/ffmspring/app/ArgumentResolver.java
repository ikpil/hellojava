package com.ikpil.hello.java.spring.ffmspring.app;

import java.io.InputStream;

public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}
