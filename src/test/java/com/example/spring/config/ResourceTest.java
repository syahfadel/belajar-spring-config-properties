package com.example.spring.config;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

public class ResourceTest {
    /*
     * Sebelum belajar config properties di spring kita harus belajar mengenai
     * resource terlebih dahulu. Spring membungkus resource dalam sebuah interface
     * bernama Resource. walaupun Resource merupakan interface, kita tidak perlu
     * mengimplementasikannya secara manual, sudah banyak implemetasi class resource
     * di spring.
     * 
     * mencoba yang sederhana dengan ClassPathResource() akan mencari file didalam
     * project kita. kenapa direkomendasikan menggunakan class ini dibanding
     * langsung file system, karena saat file dipindah file system akan
     * berbeda-beda. kalau class path cukup simpan di project kita, nanti dia akan
     * mecari didalam project kita. misal /text/sample.txt dia akan mencari di
     * default package lalu mencari file text lalu mencari file sample.txt
     * 
     * harus tahu resource dahulu karena saat melakukan config properties kita perlu
     * tahu dimana lokasi file config propertiesnya
     */

    @Test
    void testResource() throws IOException {
        var resource = new ClassPathResource("/text/sample.txt");

        Assertions.assertNotNull(resource);

        Assertions.assertTrue(resource.exists());
        Assertions.assertNotNull(resource.getFile());
    }

}
