package com.example.demo

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class HtmlController {

  @GetMapping("/")
  fun index(): String {
    return "Hello world!"
  }

}
