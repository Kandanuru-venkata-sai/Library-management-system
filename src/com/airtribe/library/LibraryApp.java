package com.airtribe.library;

import com.airtribe.library.entity.Book;
import com.airtribe.library.entity.Branch;
import com.airtribe.library.entity.Library;
import com.airtribe.library.entity.Patron;
import com.airtribe.library.service.AvailableBooksRecommendation;
import com.airtribe.library.service.HistoryBasedRecommendation;

public class LibraryApp {

    public static void main(String[] args) {

        Library library =
                new Library(new HistoryBasedRecommendation());

        System.out.println("=== BOOK MANAGEMENT ===");

        System.out.println("Add Clean Code: " +
                library.addBook(
                        "Clean Code",
                        "ISBN001",
                        "Robert Martin",
                        2008
                ));

        System.out.println("Add Clean Architecture: " +
                library.addBook(
                        "Clean Architecture",
                        "ISBN002",
                        "Robert Martin",
                        2017
                ));

        System.out.println("Add Design Patterns: " +
                library.addBook(
                        "Design Patterns",
                        "ISBN003",
                        "Erich Gamma",
                        1994
                ));

        System.out.println("Add duplicate ISBN: " +
                library.addBook(
                        "Another Book",
                        "ISBN001",
                        "Someone",
                        2020
                ));

        System.out.println("\n=== PATRON MANAGEMENT ===");

        System.out.println("Add Sai: " +
                library.addPatron("Sai", 101));

        System.out.println("Add Rahul: " +
                library.addPatron("Rahul", 102));

        System.out.println("Add duplicate patron ID: " +
                library.addPatron("Someone Else", 101));

        Patron sai = library.findPatronById(101);
        Patron rahul = library.findPatronById(102);

        System.out.println("\n=== SEARCH ===");

        Book cleanCode =
                library.searchBookByISBN("ISBN001");

        System.out.println("Search ISBN: " + cleanCode);

        System.out.println("Search title: " +
                library.searchBookByTitle("Clean Code"));

        System.out.println("Search author: " +
                library.searchBooksByAuthor("Robert Martin"));

        System.out.println("\n=== UPDATE ===");

        Book updatedBook =
                new Book(
                        "Clean Code - Updated",
                        "ISBN001",
                        "Robert Martin",
                        2009
                );

        System.out.println("Update book: " +
                library.updateBook(updatedBook));

        Patron updatedPatron =
                new Patron("Sai Updated", 101);

        System.out.println("Update patron: " +
                library.updatePatron(updatedPatron));

        System.out.println("\n=== LENDING ===");

        cleanCode =
                library.searchBookByISBN("ISBN001");

        System.out.println("Lend Clean Code: " +
                library.lendABook(cleanCode, sai));

        System.out.println("Book available: " +
                cleanCode.getIsAvailable());

        System.out.println("Borrowed books: " +
                sai.getBorrowedList().size());

        System.out.println("Lend same book again: " +
                library.lendABook(cleanCode, sai));

        System.out.println("\n=== RESERVATION / OBSERVER ===");

        System.out.println("Rahul reserves Clean Code: " +
                library.reserveBook(cleanCode, rahul));

        System.out.println("Rahul reserves again: " +
                library.reserveBook(cleanCode, rahul));

        System.out.println("\n=== INVENTORY ===");

        System.out.println(library.statusOfBooks());

        System.out.println("\n=== RETURN ===");

        System.out.println("Return Clean Code: " +
                library.returnABook(cleanCode, sai));

        System.out.println("Book available: " +
                cleanCode.getIsAvailable());

        System.out.println("\n=== HISTORY BASED RECOMMENDATION ===");

        System.out.println(
                library.getRecommendations(sai)
        );

        System.out.println("\n=== CHANGE STRATEGY ===");

        library.setRecommendationStrategy(
                new AvailableBooksRecommendation()
        );

        System.out.println(
                library.getRecommendations(sai)
        );

        System.out.println("\n=== MULTI-BRANCH ===");

        System.out.println("Add Main Branch: " +
                library.addBranch(1, "Main Branch"));

        System.out.println("Add City Branch: " +
                library.addBranch(2, "City Branch"));

        Branch mainBranch =
                library.findBranchById(1);

        Branch cityBranch =
                library.findBranchById(2);

        Book designPatterns =
                library.searchBookByISBN("ISBN003");

        mainBranch.addBook(designPatterns);

        System.out.println("Main Branch: " +
                mainBranch.getBooks());

        System.out.println("City Branch: " +
                cityBranch.getBooks());

        System.out.println("Transfer Design Patterns: " +
                library.transferBook(
                        designPatterns,
                        1,
                        2
                ));

        System.out.println("Main Branch after transfer: " +
                mainBranch.getBooks());

        System.out.println("City Branch after transfer: " +
                cityBranch.getBooks());

        System.out.println("\n=== FINAL INVENTORY ===");

        System.out.println(library.statusOfBooks());

        System.out.println("\n=== TEST COMPLETED ===");
    }
}