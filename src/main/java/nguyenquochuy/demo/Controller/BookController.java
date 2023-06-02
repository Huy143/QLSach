package nguyenquochuy.demo.Controller;

import nguyenquochuy.demo.entity.Book;
import nguyenquochuy.demo.entity.Category;
import nguyenquochuy.demo.services.BookService;
import nguyenquochuy.demo.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBooks(Model model){
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books",books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBooks(@Valid @ModelAttribute("Book") Book bookInput, BindingResult bindingResult, Model model){
        if (bindingResult != null && bindingResult.hasErrors()){
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList();
            model.addAttribute("errors", errors);
            return "book/add";
        }
        bookInput.setId(bookService.getAllBooks().stream().count()+1);
        bookService.addBook(bookInput);
        return "redirect:/book";
    }
    @GetMapping("/edit/{id}")
    public String editBooks(@PathVariable("id") Long id, Model model){
        for(Book book : bookService.getAllBooks()){
            if (book.getId().equals(id)){
                model.addAttribute("book", book);
                model.addAttribute("categories", categoryService.getAllCategories());
                return "book/edit";
            }
        }
        return "not-found";
    }

    @PostMapping("/edit")
    public String editBooks(@Valid Book updateBook, Errors errors, Model model) {
        if (errors != null && errors.getErrorCount() > 0) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }
        // Logic để xử lý dữ liệu sách đã chỉnh sửa và lưu vào cơ sở dữ liệu
        bookService.updateBook(updateBook);

        // Chuyển hướng người dùng đến trang danh sách sách
        return "redirect:/book";
    }
    @GetMapping("/delete/{id}")
    public String deleteBooks(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/book";
    }
}
