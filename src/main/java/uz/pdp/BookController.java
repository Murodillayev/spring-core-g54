package uz.pdp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        BOOKS.add(book.validate());
        return "redirect:/book";
    }

    @GetMapping("/add")
    public ModelAndView addPage(Model model) {
        return new ModelAndView("add");
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Book book) {

        BOOKS.add(book.validate());
        return "redirect:/book";
    }



}
