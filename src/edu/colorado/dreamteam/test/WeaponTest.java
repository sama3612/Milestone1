package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.Weapon;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WeaponTest {
    @Test
    public void canMakeSonarPulse() {
        Weapon weapon = new Weapon("sonar_pulse");
        assertThat(weapon.getWeaponType(), is("sonar_pulse"));
    }
}

