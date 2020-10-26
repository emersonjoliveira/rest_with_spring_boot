package br.com.emerson.services;

import br.com.emerson.adapter.DozerAdapter;
import br.com.emerson.data.model.Book;
import br.com.emerson.data.vo.v1.BookVO;
import br.com.emerson.exception.ResourceNotFoundException;
import br.com.emerson.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private static final String RECORDS_NOT_FOUND_FOR_THIS_ID = "No records for this ID!";

    @Autowired
    BookRepository repository;

    public BookVO create(BookVO vo) {
        var entity = DozerAdapter.parseObject(vo, Book.class);
        return DozerAdapter.parseObject(repository.save(entity), BookVO.class);
    }

    public Page<BookVO> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(this::convertToBookVO);
    }

    private BookVO convertToBookVO(Book entity) {
        return DozerAdapter.parseObject(entity, BookVO.class);
    }

    public BookVO findById(Long id) {
        return DozerAdapter.parseObject(
                repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID)),
                BookVO.class);
    }

    public BookVO update(BookVO bookVO) {
        var entity = repository.findById(bookVO.getKey())
                .orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID));
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());

        return DozerAdapter.parseObject(repository.save(entity), BookVO.class);
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RECORDS_NOT_FOUND_FOR_THIS_ID));
        repository.delete(entity);
    }
}
