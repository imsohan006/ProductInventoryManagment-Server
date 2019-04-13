package com.product_inventory.application.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason="To show an example of a custom message")
public class CustomeException extends RuntimeException{}
