package service;

import models.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

    private static String getDescription(String url){
        String description = "";
        try {
            Document page = getPage(url);
            Elements allParagraphs = page.select("div[class=b-description__sub]").select("p");
            for (var e : allParagraphs){
                description = description + e.text() + "\n";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return description;
    }

    public static void searchBooks(String url) throws IOException {
        Document page = getPage(url);

        Elements allSections = page.select("div[class=viewer-type-card__wrapper]").select("div[class=item-type-card]");
        for (var section : allSections) {
            //String buff = section.select("div[class=item-type-card__content]").select("p[class=item-type-card__title]").text();
            String title = section.select("p[class=item-type-card__title]").text();
            String[] authorAndDate=section.select("p[class=item-type-card__info]").text().split(", ");
            String description = getDescription("https://oz.by"+section.select("a[class=needsclick item-type-card__link item-type-card__link--main]").attr("href"));
            String genre ="";
            books.add(new Book(title, authorAndDate[0], description, genre, authorAndDate[1]));
        }
    }


    public static void main(String[] args) throws IOException {
        /*while (true) {
            searchProducts();
            for (var m : products) {
                System.out.println(m);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
        System.out.println(
                getPage("https://nice-books.ru/books/")
        );
        //searchBooks("https://oz.by/books/");

    }
}