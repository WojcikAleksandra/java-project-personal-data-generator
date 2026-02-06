# Generator fikcyjnych danych osobowych  

Niniejsza aplikacja służy do generowania listy fikcyjnych danych osobowych i eksportowania ich do pliku Excela (w formacie XLS lub XLSX). Liczba generowanych osób oraz preferowany format pliku podawane są przez użytkownika w interfejsie graficznym aplikacji.  

## Generowane elementy  
* *Imię i nazwisko*  
  Losowo tworzone są kombinacje imion i nazwisk z list najpopularniejszych imion i nazwisk w Polsce, z uwzględnieniem form żeńskich i męskich.
* *Płeć*  
  Losowana jest płeć - kobieta/mężczyzna, zgodna z imieniem i nazwiskiem.
* *Adres zamieszkania*  
  Obejmuje nazwę ulicy, numer budynku, numer mieszkania oraz nazwę miasta lub wsi. Dane generowane są z listy nazw istniejących ulic oraz z uwzględnieniem populacji miast w Polsce.
* *Data urodzenia*  
  Losowane są daty urodzenia zawierające się w realistycznych ramach czasowych.
* *Numer telefonu*  
  Tworzone są numery telefonów zgodne z obowiązujacym w Polsce formatem i unikalne dla każdej osoby.
* *Numer PESEL*  
  Tworzone są numery PESEL zgodne z obowiązującym formatem i z pozostałymi danymi osoby (data urodzenia, płeć). Pozostałe cyfry numeru PESEL są losowane, ale z zachowanym warunkiem unikalności.
* *Relacje pokrewieństwa i małżeństwa*  
  Losowane i przypisywane są relacje między wygenerowanymi osobami. Możliwe relacje: ojciec, matka, dzieci, siostry, bracia, mąż/żona.

## Interfejs użytkownika  
* Miejsce do wpisania liczby generowanych osób z zakresu 100 - 1 000 000.
* Wybór typu pliku XLS/XLSX z informacją o możliwym eksporcie do pliku XLS tylko do 64 000 osób.
* Przycisk "Uruchom"
* W przypadku wpisania błędnej liczby osób wyświetlany jest odpowiedni komunikat.

## Raport z wydajności aplikacji  
W interfejsie użytkownika znajduje się okno, w którym wypisywane są wyniki pomiarów czasu działania aplikacji dla poszczególnych rozmiarów zadania, jeden pod drugim. Czas kompilacji w milisekundach wyraża się wzorem: 0.1 * n * a, gdzie  5 > a > 0.2, n jest liczba osób do wygenerowania. Wzór może słabo działać dła małych n, ale wtedy czas nas średnio interesuje.
