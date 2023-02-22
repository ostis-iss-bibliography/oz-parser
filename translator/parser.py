import requests
from bs4 import BeautifulSoup

import translator
from book import Book


def parse_child_page(url, book_to_update):
    child_response = requests.get(url)
    child_soup = BeautifulSoup(child_response.text, 'lxml')
    html = child_soup.find('div', class_='iszfik-12 clyKYY')

    book_to_update.released_on = child_soup.find('div', class_='iszfik-14 iSnZQd').find_next('dd', class_='iszfik-18 iEusfO').text
    book_to_update.russian_description = \
    child_soup.find('div', class_='iszfik-2 gAFRve').text.split('читайте онлайн' or 'Больше интересных фактов')[0]
    book_to_update.russian_genre = "Фантастика"
    return book_to_update


def translate_russian_info(book_to_update):
    book_to_update.genre = translator.translate_into_english(book_to_update.russian_genre)
    book_to_update.name = translator.translate_into_english(book_to_update.russian_name)
    book_to_update.publisher = translator.translate_into_english(book_to_update.russian_publisher)
    book_to_update.description = translator.translate_into_english(book_to_update.russian_description)
    return book_to_update


URL = 'https://mybook.ru/catalog/fantastika/books/'
response = requests.get(URL)
soup = BeautifulSoup(response.text, 'lxml')
quotes = soup.find_all('div', class_='e4xwgl-0 iJwsmp')

for quote in quotes:
    book = Book()
    child_url = "https://mybook.ru" + quote.find_next('div', class_='e4xwgl-1 gEQwGK').find_next('a').get('href')
    book.russian_name = quote.find_next('p', class_='lnjchu-1 hhskLb').text
    book.russian_publisher = quote.find_next('div', class_='dey4wx-1 jVKkXg').text
    book = parse_child_page(child_url, book)
    book = translate_russian_info(book)
    print(book.russian_name+", "+book.name+", " + book.genre+", "+book.publisher + ", "+book.released_on+"\n"+book.description +"\n\n")


#print(soup)