package communication;


public enum Instruction {
    BAD_HEADER("W pliku nie ma poprawnych nagłówków."),
    NO_MANUFACTURERS_TABLE("Błąd wczytywania producentów."),
    NO_PHARMACIES_TABLE("Błąd wczytywania aptek."),
    NO_CONNECTIONS_TABLE("Błąd wczytywania połączeń."),
    NO_FILE("Brak pliku."),
    CLOSED_INPUT_CHOOSER_WINDOW("Nie wybrano pliku wejściowego."),
    CLOSED_OUTPUT_CHOOSER_WINDOW("Nie wybrano pliku wyjściowego."),

    BAD_ID_MANUFACTURER(
            "ID producenta w tabeli połączeń nie ma odwzorowania w tabeli producenci. Błąd w linii: "),
    BAD_ID_PHARMACY(
            "ID apteki w tabeli połączeń nie ma odwzorowania w tabeli apteki. Błąd w linii: "),

    DUPLICATE("Id powinno być unikalne. Id powtarza się w linii: "),
    SOME_UNNEEDED_VALUE("Nieprawidłowy koniec pliku. Napotkano na niepotrzebną wartość."),
    LACK_CONNECTION_FOR_EVERY_PAIR(
            " Plik powinien zawierać połączenie dla każdej pary Apteka - Producent."),
    HIGHER_DEMAND_THAN_SUPPLY(
            " Zapotrzebowanie aptek musi być niższe niż suma całkowitej produkcji."),
    LIMITS_LOWER_THAN_DEMAND(
            " Suma dziennych możliwych dostaw wynikających z umów musi być taka jak lub większa niż popyt dla każdej apteki. Błąd dla apteki: "),
    MINIMIZATION_IMPOSSIBLE("Minimalizacja niemożliwa."),
    LONG_FILE("Ze względu na rozmiar pliku generowanie wyników może potrwać nieco dłużej."),
    SAVED_FILE("Plik został zapisany. Możesz zamknąć aplikację albo wybrać kolejny plik.");

    public final String instruction;

    Instruction(String msg) {
        this.instruction = msg;
    }
}
