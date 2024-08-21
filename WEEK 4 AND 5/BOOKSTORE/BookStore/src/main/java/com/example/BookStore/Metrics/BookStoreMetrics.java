package com.example.BookStore.Metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class BookStoreMetrics {

    private final Counter bookCreatedCounter;
    private final Counter bookReadCounter;
    private final Counter bookUpdatedCounter;
    private final Counter bookDeletedCounter;

    public BookStoreMetrics(MeterRegistry meterRegistry) {
        this.bookCreatedCounter = meterRegistry.counter("bookstore.books.created");
        this.bookReadCounter = meterRegistry.counter("bookstore.books.read");
        this.bookUpdatedCounter = meterRegistry.counter("bookstore.books.updated");
        this.bookDeletedCounter = meterRegistry.counter("bookstore.books.deleted");
    }

    public void incrementBookCreatedCount() {
        bookCreatedCounter.increment();
    }

    public void incrementBookReadCount() {
        bookReadCounter.increment();
    }

    public void incrementBookUpdatedCount() {
        bookUpdatedCounter.increment();
    }

    public void incrementBookDeletedCount() {
        bookDeletedCounter.increment();
    }
}
