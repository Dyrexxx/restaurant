package ru.pizza.restaurant.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NoContentException.class})
    public ModelAndView handleNoContentException(NoContentException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        if (exception.getId() != null) {
            model.addAttribute("id", exception.getId());
        }
        return new ModelAndView(exception.getPage(), model.asMap());
    }
}
