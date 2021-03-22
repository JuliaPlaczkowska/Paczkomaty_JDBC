/**
 * Enum class containing names of parcels' status'
 */

public enum StatusPrzesylki {
    utworzona(1), nadana(2), w_dostawie(3), dostarczona(4), odebrana(5);

    int value;

    StatusPrzesylki(int value){this.value=value;}


}
