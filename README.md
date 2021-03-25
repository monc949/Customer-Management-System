# Customer Management System

This is a Stock, Customer and Invoice management system
written in Java with a Swing GUI.

It is intended to be an assignment for the Object Orientated
Software Development Module of

the Software Development Course in Institute of Technology
Carlow.

It is structured using a MVC (Model, View, Controller)
architecture.

## Main Screen

The main screen of the application is the Invoice Builder.
It consists of a “Top Panel”, a “Side Panel” and a “Centre Panel” displaying
the “Shopping Cart” From this screen, the user can select a customer from the
customer database and add items from the product database to a cart. The user
can then submit the order, clearing the cart and creating a new record in the
orders table.

The order can then be viewed in the orders table. The order
contains a total price and the date of
purchase, along with the corresponding customer.

The details of any Order can be seen using the Order Details function on the Order Management Screen.

From this main screen, three other database management
screens can be accessed from the buttons on the top panel.

The refresh button will refresh the contents of the Combo
Box and Product List when a new Customer/Product has been added to the system.

## Database Management Screens

These screens contain a table showing the contents of the
corresponding table in the database.

In the sidebar, text fields are used to enter information
and are followed by buttons to create/edit etc...
