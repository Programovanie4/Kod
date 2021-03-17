//interface Hra {
interface Hra<Karta> {
    Karta[] deck();
    void miesaj();
    void utried();
}