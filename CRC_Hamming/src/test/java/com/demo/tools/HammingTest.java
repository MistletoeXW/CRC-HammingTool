package com.demo.tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class HammingTest {

    @Test
    public void getHaiMingCode() {
        Hamming hamming = new Hamming();
        System.out.print(hamming.getHaiMingCode("1010110"));
    }
}