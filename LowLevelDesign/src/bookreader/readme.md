# Design Online Book Reader System

Let’s assume we want to design a basic online reading system which provides the following functionality:

- Searching the database of books and reading a book.
- User membership creation and extension.
- Only one active user at a time and only one active book by this user

The class OnlineReaderSystem represents the body of our program. 
We could implement the class such that it stores information about all the books, 
deals with user management, and refreshes the display, but that would make this class rather hefty.Instead, 
we’ve chosen to tear off these components into Library, UserManager, and Display classes.

