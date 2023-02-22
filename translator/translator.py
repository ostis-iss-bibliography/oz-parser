from translate import Translator


def translate_into_english(string):

    translator = Translator(from_lang="ru", to_lang="en")
    res = ""
    counter = 0

    while len(string) - 500*counter > 500:
        str_to_translate = ""
        for i in range(500):
            str_to_translate = str_to_translate + string[i]
        res = res + translator.translate(str_to_translate)
        counter += 1

    str_to_translate = ""
    for i in range(500*counter, len(string)):
        str_to_translate = str_to_translate + string[i]

    res = res + translator.translate(str_to_translate)

    return res



