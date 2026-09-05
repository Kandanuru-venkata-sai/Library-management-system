package com.airtribe.library.entity;

import com.airtribe.library.service.*;


import java.util.*;
import java.util.logging.Logger;
public class Library {
    private List<Book> books;
    private List<Patron> patrons;
    private SearchService searchService;
    private LendingService lendingService;
    private ReservationService reservationService;
    private RecommendationService recommendationService;
    private List<Branch> branches;
    private BranchService branchService;
    private static final Logger logger =
            Logger.getLogger(Library.class.getName());

    public Library(RecommendationStrategy recommendationStrategy) {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
        this.searchService = new SearchService();
        this.lendingService = new LendingService();
        this.reservationService = new ReservationService();
        this.recommendationService =
                new RecommendationService(recommendationStrategy);
        this.branches = new ArrayList<>();
        this.branchService = new BranchService();
    }
    public void setRecommendationStrategy(RecommendationStrategy strategy) {
        recommendationService.setStrategy(strategy);
    }
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public List<Patron> getPatrons() {
        return new ArrayList<>(patrons);
    }

    public Book searchBookByISBN(String isbn) {
        return searchService.searchBookByISBN(isbn, getBooks());
    }

    public List<Book> searchBookByTitle(String title) {
        return searchService.searchBookByTitle(title, getBooks());
    }

    public List<Book> searchBooksByAuthor(String author) {
        return searchService.searchBooksByAuthor(author, getBooks());
    }

    public boolean addBook(String title, String isbn, String author, int publishedYear) {
        Book book = searchService.searchBookByISBN(isbn, getBooks());

        if (book == null) {
            books.add(new Book(title, isbn, author, publishedYear));
            logger.info("Book added: " + title);
            return true;
        } else {
            logger.warning("Book already exists with ISBN: " + isbn);
            return false;
        }
    }

    public boolean removeBook(String isbn) {
        Book book = searchService.searchBookByISBN(isbn, getBooks());

        if (book != null) {
            books.remove(book);
            logger.info("Book removed: " + book.getTitle());
            return true;
        }

        logger.warning("Book not found with ISBN: " + isbn);
        return false;
    }

    public boolean updateBook(Book newBook) {
        Book book = searchService.searchBookByISBN(newBook.getIsbn(), getBooks());

        if (book != null) {
            book.updateBook(
                    newBook.getTitle(),
                    newBook.getAuthor(),
                    newBook.getPublishedYear()
            );

            logger.info("Book updated: " + newBook.getIsbn());
            return true;
        }

        logger.warning("Book not found for update: " + newBook.getIsbn());
        return false;
    }

    public boolean addPatron(String patronName, int id) {
        Patron patron = findPatronById(id);

        if (patron == null) {
            patrons.add(new Patron(patronName, id));
            logger.info("Patron added: " + patronName);
            return true;
        }

        logger.warning("Patron already exists with ID: " + id);
        return false;
    }

    public Patron findPatronById(int id) {
        for (Patron p : patrons) {
            if (p.getPatronId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean updatePatron(Patron newPatron) {
        Patron patron = findPatronById(newPatron.getPatronId());

        if (patron != null) {
            patron.updatePatron(newPatron.getPatronName());
            logger.info("Patron updated: " + newPatron.getPatronId());
            return true;
        }

        logger.warning("Patron not found for update: " + newPatron.getPatronId());
        return false;
    }
    public boolean lendABook(Book book, Patron patron){
        return lendingService.lendABook(book, patron, getBooks(), getPatrons());
    }

    public boolean returnABook(Book book, Patron patron){
        return lendingService.returnABook(book, patron, getBooks(), getPatrons());
    }

    public Map<String,List<Book>> statusOfBooks(){
        return lendingService.statusOfBooks(getBooks());
    }
    public boolean reserveBook(Book book, Patron patron) {
        return reservationService.reserveBook(book, patron);
    }
    public List<Book> getRecommendations(Patron patron) {
        return recommendationService.getRecommendations(patron, getBooks());
    }
    public boolean addBranch(int branchId, String branchName) {

        if (findBranchById(branchId) != null) {
            return false;
        }

        branches.add(new Branch(branchId, branchName));
        return true;
    }

    public Branch findBranchById(int branchId) {

        for (Branch branch : branches) {
            if (branch.getBranchId() == branchId) {
                return branch;
            }
        }

        return null;
    }

    public List<Branch> getBranches() {
        return new ArrayList<>(branches);
    }
    public boolean transferBook(Book book, int fromBranchId, int toBranchId) {

        Branch fromBranch = findBranchById(fromBranchId);
        Branch toBranch = findBranchById(toBranchId);

        return branchService.transferBook(book, fromBranch, toBranch);
    }
}


