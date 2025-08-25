package com.sehoon.springbootPractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // resources에 있는 hello.html을 로딩해라.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name",name);
        return "hello-template.html";
    }

    @GetMapping("hello-string")
    @ResponseBody // http에서 body부분에 이 데이터를 내가 직접 넣어주겠다는 의미.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // name == Spring; return "hello Spring";
    }

    // Json으로 데이터가 내려간다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // 객체가 넘어오면 Json형식으로 넘겨주겠다고 만들어둠
        // @ResponseBody를 사용하면 -> HttpMessageConverter가 동작을 함.
        // 이게 단순 문자면 StringConverter가 동작함.
        // 객체면 JsonConverter가 동작함.(MappingJackson2HttpMessageConverter)
        // byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어있다.
        // 클라이언트의 Http Accep헤더와 서버의 컨트롤러 반환 타입 정보를 조합해서 컨버터가 선택됨.
    }

    static class Hello{
        private String name;

        // 자바 빈 규약 (Getter Setter)
        // 프로퍼티 접근 방식이라고 부름.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
