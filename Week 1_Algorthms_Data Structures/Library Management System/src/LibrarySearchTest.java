public class LibrarySearchTest {
    public static void main(String[] args) {
        Book[] books = {
            new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B002", "1984", "George Orwell"),
            new Book("B003", "To Kill a Mockingbird", "Harper Lee")
        };

        // Linear Search
        Book result = LibrarySearch.linearSearch(books, "1984");
        if (result != null) {
            System.out.println("Linear Search: Found " + result.getTitle() + " by " + result.getAuthor());
        } else {
            System.out.println("Linear Search: Book not found");
        }

        // Binary Search (assuming the array is sorted)
        result = LibrarySearch.binarySearch(books, "1984");
        if (result != null) {
            System.out.println("Binary Search: Found " + result.getTitle() + " by " + result.getAuthor());
        } else {
            System.out.println("Binary Search: Book not found");
        }
    }
}
