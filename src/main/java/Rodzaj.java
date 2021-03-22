/**
 * enum class containing names of parcel's types
 */
public enum Rodzaj {
    paczka(1), koperta(2), nietypowa(3);

    int value;

    Rodzaj(int value){this.value=value;}
}