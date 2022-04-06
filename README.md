# bank services

API
1, Get customer details
- URI: GET http://localhost:8072/api/account/customers/{customerId}

2, Sigup
- URI: POST http://localhost:8072/api/auth/users/signup
- Request:
- {
    "username": "",
    "password": "",
    "email": "",
    "dateOfBirth": "yyyy-mm-dd",
    "roles": [
        "ROLE_USER"
    ]
}

3, Signin
- URI: POST http://localhost:8072/api/auth/authenticate/signin
- Request:
- {
    "username": "",
    "password": ""
}

4, Cards
- URI: POST http://localhost:8072/api/card/cards
- Request:
- {
    "customerId": ""
}

5, Loans
- URI: POST http://localhost:8072/api/loan/loans
- Request:
- {
    "customerId": ""
}

6, Accounts:
- URI: http://localhost:8072/api/account/accounts
- Request:
- {
    "customerId": ""
}
