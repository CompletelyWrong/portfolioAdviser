# Portfolio adviser

Api for investment portfolio management tips

Endpoint: "http://localhost:8080/api/V1/portfolio/"

Parameters:
- Type: required (crazy, balanced, safe)
- Currency : optional(currency=USD)
- Country : optional(country=USA)
- StartDate : optional(startDate=2010-10-10), format: yyyy-mm-dd

Example: http://localhost:8080/api/V1/portfolio/crazy?currency=USD&country=USA&startDate=2010-10-10