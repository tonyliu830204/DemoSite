## API
### 1. Common
#### 1.1 Exception
If a exception is thrown while processing the request, the server will return a 500 response containing a header 'ErrorCode'

### 2. Customer

#### 2.1 Register
##### 2.1.1 Request
POST /api/v1/customers/register
```json
{
  "name":"XXX",
  "email":"XXX",
  "password":"xxx",
  "passwordConfirm":"xxx"
}
```
#### 2.1.2 Response
```json
{
  "id":"123",
  "firstName":"name",
  "email":"email"
}
```

#### 2.2 Login
##### 2.2.1 Request
POST /api/v1/customers/login
```json
{
    "email":"xxx",
    "password":"xxx"
}
```
##### 2.2.2 Response
If login successfully, will return the customer data, or will return a 500 response.

### 3. Product
#### 3.1 查看产品分类
##### 3.1.1 REQUEST
GET /api/v1/products/categories/{id}
##### 3.1.2 RESPONSE
```json
{
  "categories": [
    {
      "id":"123",
      "name":"xxx",
      "desc":"xxx",
      "iconURL":"xxx"
    }
  ],
  
  "products": [
	  {
      "id": "",
      "name": "",
      "iconURL": "",
      "desc": "",
      "price": ""
    }
  ]
}
```

### 3. Checkout
#### 3.1 performCheckout
##### 3.1.1 Request
* POST /api/v1/order
* Headers
  * Authorization: Basic `base64_encode(username:password)`


```json
{
    "name":"xxx",
    "phone":"186",
    "address":"xxx",
    "products":[
        {
            "productId":"xxx",
            "quantity":1,
            "price":9.99,
            "skuId":123
        }
    ],
    "payment":"alipay"
}
```

#### 3.2 Order History
##### 3.2.1 Request
* GET /api/v1/orders
* Headers
  * Authorization: Basic `base64-encode(username:password)`

##### 3.2.2 Response
```json
[
    {
        "name": "weinan",
        "phone": "111",
        "address": "test",
        "subTotal": "14.99",
        "status":"SUBMITTED/IN_PROCESS/CANCELLED",
        "products": [
            {
                "productId": 100,
                "quantity": 1,
                "price": "14.99",
                "skuId": 111
            }
        ]
    }
]
```