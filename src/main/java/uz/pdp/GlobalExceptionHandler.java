package uz.pdp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import uz.BookNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BookNotFoundException.class})
    public ModelAndView handleBookNotFoundException(BookNotFoundException e, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("error/404");
        modelAndView.addObject("errorMess", e.getMessage());
        modelAndView.addObject("path", request.getRequestURI());

        return modelAndView;
    }

    @ExceptionHandler({BadRequestException.class})
    public ModelAndView handleBadRequestException(BadRequestException e, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("error/400");
        modelAndView.addObject("errorMess", e.getMessage());
        modelAndView.addObject("path", request.getRequestURI());
        return modelAndView;
    }


}
