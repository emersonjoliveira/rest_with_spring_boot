package br.com.emerson.services;

import br.com.emerson.data.model.Book;
import br.com.emerson.data.vo.v1.BookVO;
import br.com.emerson.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceBase<Book, BookRepository, BookVO> {

    @Override
    public Class<BookVO> getVOClass() {
        return BookVO.class;
    }

    @Override
    public Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    protected void setEntityValues(Book entity, BookVO bookVO) {
        entity.setAuthor(bookVO.getAuthor());
        entity.setLaunchDate(bookVO.getLaunchDate());
        entity.setPrice(bookVO.getPrice());
        entity.setTitle(bookVO.getTitle());
    }

    @Override
    protected Long getIdVO(BookVO vo) {
        return vo.getKey();
    }
}
