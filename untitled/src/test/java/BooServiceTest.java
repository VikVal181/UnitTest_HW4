
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import Book.Book;
import Book.BookRepository;
import Book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        // Создание мок-объекта для BookRepository
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testFindBookById() {
        // Задаем ожидаемый результат
        Book expectedBook = new Book("1", "Основы Java", "Джавист");
        when(bookRepository.findById("1")).thenReturn(expectedBook);

        // Вызываем метод и проверяем результат
        Book actualBook = bookService.findBookById("1");
        Assertions.assertEquals(expectedBook, actualBook);

        // Проверяем, что метод findById был вызван с правильным аргументом (id = "1")
        verify(bookRepository).findById("1");
    }

    @Test
    public void testFindAllBooks() {
        // Задаем ожидаемый результат
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("1", "Основы Java", "Джавист"));
        expectedBooks.add(new Book("2", "UNIT-тесты", "Л.Бадеев"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // Вызываем метод и проверяем результат
        List<Book> actualBooks = bookService.findAllBooks();
        Assertions.assertEquals(expectedBooks, actualBooks);

        // Проверяем, что метод findAll был вызван
        verify(bookRepository).findAll();
    }
}