package com.interpixel.geter_geterbersensasi;

public interface GeterListener {

    void geter(long ms);

    void stopGetar();

    void geterPattern(long[] pattern, boolean repeat);

    void geterMorse(long[] morse);

}
