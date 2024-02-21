package com.project.controller;

import com.project.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Controller:
//
//Görevi: Kullanıcı taleplerini karşılayan ve uygulama mantığını yürüten bir sınıfı temsil eder.
//Amaçları:
//HTTP isteklerini almak ve işlemek.
//Kullanıcı arayüzü (UI) ile uygulama mantığı arasında iletişimi sağlamak.
//Gerekli işlemleri gerçekleştirmek için servis sınıflarıyla etkileşimde bulunmak.
//Örnek bir Controller sınıfı, kullanıcı isteklerini ele alabilir
// ve ilgili işlemleri gerçekleştirmek üzere ilgili servis sınıflarıyla etkileşimde bulunabilir.

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;



}
