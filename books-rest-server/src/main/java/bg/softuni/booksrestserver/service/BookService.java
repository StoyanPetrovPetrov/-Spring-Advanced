package bg.softuni.booksrestserver.service;

import bg.softuni.booksrestserver.model.dto.AuthorDTO;
import bg.softuni.booksrestserver.model.dto.BookDTO;
import bg.softuni.booksrestserver.model.entity.BookEntity;
import bg.softuni.booksrestserver.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO>getAllBooks(){
        return bookRepository.findAll().stream().
                map(this::map).toList();

    }

    private BookDTO map(BookEntity bookEntity) {

        AuthorDTO authorDTO = new AuthorDTO().
                setName(bookEntity.getAuthor().getName());

        return new BookDTO().
                setId(bookEntity.getId()).
                setAuthor(authorDTO).
                setIsbn(bookEntity.getIsbn()).
                setTitle(bookEntity.getTitle());
    }
}
