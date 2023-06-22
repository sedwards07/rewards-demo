# Rewards Demo

## This is an example of a rewards calculation endpoint
* A retailer offers a rewards program to its customers, awarding points based on each recorded purchase. 
* A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent between $50 and $100 in each transaction. (e.g., a $120 purchase = 2x$20 + 1x$50 = 90 points).  
* Given a record of every transaction during a three-month period, it will calculate the reward points earned for each customer per month and total. 

## Installation
`mvn clean install`

## Test Endpoint
Call the following endpoint using the POST method
http://localhost:8080/rewards/calculate

## Sample Input
```json
[{
    "customerId": 1,
    "amount": 201.32,
    "date": "2023-04-12T13:29:56"
},
{
    "customerId": 1,
    "amount": 221.67,
    "date": "2023-04-22T13:29:56"
},
{
    "customerId": 1,
    "amount": 131.45,
    "date": "2023-05-26T13:29:56"
},
{
    "customerId": 1,
    "amount": 123.32,
    "date": "2023-06-12T13:29:56"
},
{
    "customerId": 1,
    "amount": 114.42,
    "date": "2023-06-14T13:29:56"
},
{
    "customerId": 1,
    "amount": 34.32,
    "date": "2023-06-21T13:29:56"
},
{
    "customerId": 2,
    "amount": 13.32,
    "date": "2023-05-12T13:29:56"
},
{
    "customerId": 2,
    "amount": 232.42,
    "date": "2023-04-14T13:29:56"
},
{
    "customerId": 2,
    "amount": 314.32,
    "date": "2023-06-21T13:29:56"
}]
```

## Sample Response
```json
[
    {
        "id": 1,
        "rewards": {
            "Apr": 544,
            "Jun": 174,
            "May": 112
        }
    },
    {
        "id": 2,
        "rewards": {
            "Apr": 314,
            "Jun": 478,
            "May": 0
        }
    }
]
```