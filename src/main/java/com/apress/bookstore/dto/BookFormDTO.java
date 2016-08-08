package com.apress.bookstore.dto;

import com.apress.bookstore.entity.Author;
import com.apress.bookstore.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class BookFormDTO {

    @Getter
    @Setter
    private Long id;

    @NotEmpty
    @Setter
    @Getter
    private String bookTitle;

    @NotEmpty
    @Setter
    @Getter
    private String publisherName;

    @NotNull
    @Setter
    @Getter
    private Float price;

    @NotNull
    @Setter
    @Getter
    private Set<Author> authors;

    @NotNull
    @Setter
    @Getter
    private Set<Category > categories;

    @Setter
    @Getter
    private String image;

    public BookFormDTO(String bookTitle, String publisherName, Float price, Set<Author> authors, Set<Category> categories, String image) {
        this.bookTitle = bookTitle;
        this.publisherName = publisherName;
        this.price = price;
        this.authors = authors;
        this.categories = categories;
        this.image = image;
    }

    @Override
    public String toString() {
        return "BookFormDTO{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                ", categories=" + categories +
                ", image='" + image + '\'' +
                '}';
    }

    public static class Builder {
        BookFormDTO bookFormDTO = new BookFormDTO();

        public Builder withId(Long id) {
            bookFormDTO.setId(id);
            return this;
        }

        public Builder withBookTitle(String bookTitle) {
            bookFormDTO.setBookTitle(bookTitle);
            return this;
        }

        public Builder withPublisherName(String publisherName) {
            bookFormDTO.setPublisherName(publisherName);
            return this;
        }

        public Builder withPrice(Float price) {
            bookFormDTO.setPrice(price);
            return this;
        }

        public Builder withAuthors(Set<Author> authors) {
            bookFormDTO.setAuthors(authors);
            return this;
        }

        public Builder withCategories(Set<Category> categories) {
            bookFormDTO.setCategories(categories);
            return this;
        }

        public Builder withImage(String image) {
            bookFormDTO.setImage(image);
            return this;
        }

        public BookFormDTO build() {
            return bookFormDTO;
        }

    }

}
