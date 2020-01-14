package com.ikpil.hello.java.spring.ffmspring;

import java.io.InputStream;

public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}
