package com.jerry80409;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SensorTest {

    /**
     * 離線
     */
    @Test
    void offline_is_1111_1111() {
        byte act = Sensor.enocde(false , 0);
        assertEquals(127, act);
    }

    /**
     * 0 度
     */
    @Test
    void temp_0() {
        byte act = Sensor.enocde(true , 0);
        int temp = Sensor.decode(act);
        assertEquals(0, temp);
    }

    /**
     * -40 度
     */
    @Test
    void temp_n_40() {
        byte act = Sensor.enocde(true , -40);
        int temp = Sensor.decode(act);
        assertEquals(-40, temp);
    }

    /**
     * 140 度
     */
    @Test
    void temp_140() {
        byte act = Sensor.enocde(true , 140);
        int temp = Sensor.decode(act);
        assertEquals(140, temp);
    }

}