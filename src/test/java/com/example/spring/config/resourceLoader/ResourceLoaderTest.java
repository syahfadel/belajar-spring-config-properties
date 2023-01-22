package com.example.spring.config.resourceLoader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.Setter;

@SpringBootTest(classes = ResourceLoaderTest.TestApplication.class)
public class ResourceLoaderTest {

    /*
     * spring memiliki fitur untuk mengambil data resource secara otomatis tanpa
     * harus membuat object resource namanya ResourceLoader. biasanya kita harus
     * menentukan apakah classpath resource, filesistem resource, atau url resource
     * itu menyulitkan jika kita ingin mengakses resource secara dinamis. untungnya
     * ada ResourceLoader yang terdapat function yang dapat digunakan untuk
     * mengambil resourcenya namanya getResource yang cukup memasukan lokasi dengan
     * tipa data string, resourceLoader akan mendeteksi jenis resource sesuai dengan
     * string yang dikirim. saat menambahkan string resource kita perlu menambahkan
     * prefix
     * 1. classpath:/lokasi untuk resource dari classparch atau project
     * 2. file:///lokasi mengambil resource dari file sistem
     * 3. https://urllokasi mengambil resource dari http
     * 
     * karena applicationContext merupakan turunan dari resourceLoader maka kita
     * bisa menggunakan applicationContext untuk mendapatkan resource. atau bisa
     * juga menggunakan ResourceLoaderAware untuk mendapatkan ResourceLoader secara
     * otomatis
     */

    @Autowired
    private TestApplication.SampleResource sampleResource;

    @Test
    void testResourceLoader() throws Exception {
        Assertions.assertEquals("Syah Fadel Putra Dwingga", sampleResource.getText().trim());
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        public static class SampleResource implements ResourceLoaderAware {

            @Setter
            private ResourceLoader resourceLoader;

            public String getText() throws Exception {
                Resource resource = resourceLoader.getResource("classpath:/text/resource.txt");
                try (var inputStream = resource.getInputStream()) {
                    return new String(inputStream.readAllBytes());
                }
            }

        }

    }

}
