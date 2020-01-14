package com.ikpil.hello.java.spring.ffmspring.app;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class ScannerArgumentResolver implements ArgumentResolver {
    @Override
    public Argument resolve(InputStream stream) {
        var scanner = new Scanner(stream);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        return new Argument(a, b);
    }
}
