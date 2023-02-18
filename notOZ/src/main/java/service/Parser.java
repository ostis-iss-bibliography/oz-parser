package service;

import com.google.gson.Gson;
import models.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static List<Book> books = new ArrayList<>();

    private static Document getPage(String url) throws IOException {
        Document page = Jsoup.parse(new URL(url), 250000);
        return page;
    }

    public static void searchBooks(String url) throws IOException {
        Document page = getPage(url);

        Elements allSections = page.select("div[class=movie-item ignore-select short-movie clearfix]");
        for (var section : allSections) {
            //String buff = section.select("div[class=item-type-card__content]").select("p[class=item-type-card__title]").text();
            String title = section.select("a[href]").text().split(" - ")[0];
            String author = section.select("div[class=movie-director]").select("a[href]").text();
            String date = section.select("div[class=movie-desc]").select("div[class=movie-date]").text();
            String description = section.select("div[class=movie-desc]").select("div[class=movie-text]").text();
            String genre = section.select("div[class=movie-tags nowrap]").text();
            books.add(new Book(title, author, description, genre, date));
        }
    }


    public static void main(String[] args) throws IOException {

        for (int i = 1; i < 100; i++) {
            searchBooks("https://nice-books.ru/books/page/" + i);
        }

        Gson gson = new Gson();
        String json="";
        for(var b : books){
            System.out.println(b);
            json = gson.toJson(b)+"\n\n";
        }
        //System.out.println(json);

        System.out.println(books.size());

    }
}