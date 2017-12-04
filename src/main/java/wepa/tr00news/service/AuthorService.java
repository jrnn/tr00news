package wepa.tr00news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Author;
import wepa.tr00news.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author saveAuthor(String name) {
        Author author = authorRepository.findByNameIgnoreCase(name);

        if (name.isEmpty() || author != null) {
            return null;
        }

        author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, String name) {
        Author author = authorRepository.getOne(id);

        if (name.isEmpty() || author == null) {
            return null;
        }

        author.setName(name);
        return authorRepository.save(author);
    }

    @Transactional
    public void assignArticleToAuthor(Author author, Article article) {
        if (author == null || article == null
                || author.getArticles().contains(article)
                || article.getAuthors().contains(author)) {
            return;
        }

        author.getArticles().add(article);
        article.getAuthors().add(author);
    }

    @Transactional
    public void removeArticleFromAuthor(Author author, Article article) {
        author.getArticles().remove(article);
        article.getAuthors().remove(author);
    }

}