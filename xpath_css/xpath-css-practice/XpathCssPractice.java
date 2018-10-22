package ru.yandex.webtestslessons;

// Данные элементы находятся на странице
// https://direct.yandex.ru/registered/main.pl?cmd=advancedForecast

@Name("Область для ввода ключевых фраз")
@FindBy(css = "")
private TextArea inputPhrasesArea;

@Name("Кнопка 'Посчитать'")
@FindBy(css = "")
private Button calculateButton;

@Name("Кнопка 'уточнить' регионы показа")
@FindBy(xpath = "")
private Button regionSelectorButton;

@Name("Список фраз, отображаемых в разделе 'Подсказки'")
@FindBy(xpath = "")
private List<WebElement> words;
