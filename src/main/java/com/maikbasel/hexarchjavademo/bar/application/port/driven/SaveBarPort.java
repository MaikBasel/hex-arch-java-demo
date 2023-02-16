package com.maikbasel.hexarchjavademo.bar.application.port.driven;

import com.maikbasel.hexarchjavademo.bar.domain.Bar;

public interface SaveBarPort {

    Bar saveBar(Bar bar);
}
