

package com.college.myproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movie")
    public String getMovie() {
        return "Interstellar - Sci-Fi - 2014";
    }
}