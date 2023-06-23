Solution Notes - Please read.

How it works:
- TicketService is creating a Basket using ObjectMapper (normally this functionality would be inside controller)
- After that, Basked is validated using TicketTypeRequestsValidator with all constraints mentioned in the task description.
- Total amount and number of seats are calculated inside BasketCalculator, and after that we make calls to third party PaymentService and SeatReservationService to pay and book seats

Thoughts:
- I decided to not create interface and impl classes, as there is no need. We only needed 1 solution here.
If more are needed, then we should create them.
- I'm not catching exceptions here on purpose. In Spring, I would use exception handler.