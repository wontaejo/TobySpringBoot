package com.study.springboot;

public interface HelloService {
    String sayHello(String name);

   default int countOf(String name) {
       return 0;
   }
}
