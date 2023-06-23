Solution Notes - Please read.

How it works:
- TicketService is creating a Basket using ObjectMapper (normally this functionality would be inside controller)
- After that, Basket is validated using TicketTypeRequestsValidator with all constraints mentioned in the task description.
- Total amount and number of seats are calculated inside BasketCalculator, and after that we make calls to third party PaymentService and SeatReservationService to pay and book seats

Notes:
- I decided to not create interface and impl classes, as there would be only 1 implementation of the interface.
- Exception handling should be updated depending on the projects. (e.q ExceptionHandler in Spring)