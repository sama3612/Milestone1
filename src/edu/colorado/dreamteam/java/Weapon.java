package edu.colorado.dreamteam.java;

public class Weapon {
    private String weaponType;

    /**
     * Weapon Constructor based on the weapon type specified
     * @param weaponType
     */
    public Weapon(String weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Getter for the weapon type
     * @return
     */
    public String getWeaponType() {
        return weaponType;
    }
}
