package com.aiconoa.slideshow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class SlideshowNotFoundException extends RuntimeException {

}
