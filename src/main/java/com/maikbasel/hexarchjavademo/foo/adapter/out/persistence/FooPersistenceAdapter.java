package com.maikbasel.hexarchjavademo.foo.adapter.out.persistence;

import com.maikbasel.hexarchjavademo.foo.application.port.driven.SaveFooPort;
import com.maikbasel.hexarchjavademo.foo.domain.Foo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class FooPersistenceAdapter implements SaveFooPort {

    private final FooDao fooDao;
    private final FooPersistenceMapper fooMapper;

    @Override
    public Foo saveFoo(Foo foo) {
        var persistedFoo = fooDao.save(fooMapper.toFooEntity(foo));

        return fooMapper.toFoo(persistedFoo);
    }
}
