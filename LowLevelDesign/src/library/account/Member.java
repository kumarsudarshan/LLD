package library.account;

import library.book.BookItem;
import library.models.BookStatus;
import library.models.Constants;
import library.models.ReservationStatus;
import library.reservation.BookLending;
import library.reservation.BookReservation;
import library.reservation.Fine;

import java.time.LocalDate;
import java.util.Date;

public class Member extends Account {
    private String memberId;
    private Date dateOfMembership;
    private int totalBooksCheckedout;

    public int getTotalBooksCheckedout() {
        return 0;
    }

    public boolean reserveBookItem(BookItem bookItem) {
        return false;
    }

    private void incrementTotalBooksCheckedout() {
    }

    public boolean checkoutBookItem(BookItem bookItem) {
        if (this.getTotalBooksCheckedout() >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
            ShowError("The user has already checked-out maximum number of books");
            return false;
        }
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null && bookReservation.getMemberId() != this.getId()) {
            // book item has a pending reservation from another user
            ShowError("This book is reserved by another member");
            return false;
        } else if (bookReservation != null) {
            // book item has a pending reservation from the give member, update it
            bookReservation.setStatus(ReservationStatus.COMPLETED);
        }

        if (!bookItem.checkout(this.getId())) {
            return false;
        }

        this.incrementTotalBooksCheckedout();
        return true;
    }

    private void ShowError(String s) {
    }

    private void checkForFine(String bookItemBarcode) {
        BookLending bookLending = BookLending.fetchLendingDetails(bookItemBarcode);
        Date dueDate = bookLending.getDueDate();
        Date today = new Date();
        // check if the book has been returned within the due date
        if (today.compareTo(dueDate) > 0) {
            long diff = new Date().getTime() - dueDate.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            Fine.collectFine(memberId, diffDays);
        }
    }

    public void returnBookItem(BookItem bookItem) {
        this.checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null) {
            // book item has a pending reservation
            bookItem.updateBookItemStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
        }
        bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
    }

    public boolean renewBookItem(BookItem bookItem) {
        this.checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        // check if this book item has a pending reservation from another member
        if (bookReservation != null && bookReservation.getMemberId() != this.memberId) {
            ShowError("This book is reserved by another member");
            // member.decrementTotalBooksCheckedout();
            bookItem.updateBookItemState(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
            return false;
        } else if (bookReservation != null) {
            // book item has a pending reservation from this member
            bookReservation.setStatus(ReservationStatus.COMPLETED);
        }
        BookLending.lendBook(bookItem.getBarcode(), this.memberId);
        bookItem.setDueDate(LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS));
        return true;
    }
}