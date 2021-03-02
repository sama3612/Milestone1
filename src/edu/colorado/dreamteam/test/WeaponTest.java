package edu.colorado.dreamteam.test;

import edu.colorado.dreamteam.java.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class WeaponTest {
    @Test
    public void canMakeSonarPulse() {
        Weapon weapon = new Weapon("sonar_pulse");
        assertThat(weapon.isSonarPulse(), is(true));
    }
}

