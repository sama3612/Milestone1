package edu.colorado.dreamteam.java;

public class Weapon {
    private String weaponType;

    public Weapon(String weaponType) {
        this.weaponType = weaponType;
    }

    public boolean isSonarPulse() {
        return weaponType.equals("sonar_pulse");
    }
}
