package uz.pdp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.BookNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping({"/book", "/"})
public class BookController {

    private static final List<Book> BOOKS;

    static {
        BOOKS = new ArrayList<>(
                List.of(
                        new Book("Otamdan qolgan dalalar", "Togay Murod", 2000, 200),
                        new Book("O'tkan kunlar", "Abdulla Qodiriy", 1999, 122)
                )

        );
    }


    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("books", BOOKS);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable(name = "id") String id) {

        Book book = BOOKS.stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow(
                () -> new BookNotFoundException("Book not found with id " + id)
        );

        System.out.println(book);
        model.addAttribute("book", book);
        model.addAttribute("test", "SALOM");
        return "edit";
    }

    @PostMapping("/edit")
    public String editPage(@ModelAttribute Book book) {
        BOOKS.removeIf(o -> o.getId().equals(book.getId()));
        BOOKS.add(book);
        return "redirect:/book";
    }

    @GetMapping("/add")
    public ModelAndView addPage(Model model) {
        ModelAndView mav = new ModelAndView("add");
        mav.addObject("dto", new Book());
        return mav;
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "dto") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            return "add";
        }
        BOOKS.add(book);
        return "redirect:/book";
    }


}
