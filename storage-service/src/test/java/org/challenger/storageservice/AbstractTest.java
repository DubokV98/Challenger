package org.challenger.storageservice;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

/**
 * Abstract test that helps to initialize objects annotated with
 * Mockito annotations: @Mock, @Spy, @Captor, @InjectMocks
 */
public abstract class AbstractTest {

    private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
}