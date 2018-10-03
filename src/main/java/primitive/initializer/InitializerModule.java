package primitive.initializer;

import com.google.inject.AbstractModule;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

class InitializerInjectionListener implements InjectionListener<Initializer> {
    @Override
    public void afterInjection(Initializer initializer) {
        try {
            initializer.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// todo doc
class InitializerTypeListener implements TypeListener {
    @Override
    public <Initializer> void hear(TypeLiteral<Initializer> typeLiteral, TypeEncounter<Initializer> typeEncounter) {
        typeEncounter.register((MembersInjector<? super Initializer>) new InitializerInjectionListener());
    }
}

// todo doc
class InitializerMatcher extends AbstractMatcher<TypeLiteral> {
    @Override
    public boolean matches(TypeLiteral typeLiteral) {
        return Initializer.class.isAssignableFrom(typeLiteral.getRawType());
    }
}

// todo doc
public class InitializerModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        bindListener(new InitializerMatcher(), new InitializerTypeListener());
    }
}
