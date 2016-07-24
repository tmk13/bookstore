package com.apress.bookstore.dto;

import com.apress.bookstore.entity.Author;
import com.apress.bookstore.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

public class BookFormDTO {

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

}
