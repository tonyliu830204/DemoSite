## The New Broadleaf Commerce Demo Site

This Maven project is meant to be used as a solid started project for any [Broadleaf Commerce](http://www.broadleafcommerce.org) application. It has many sensible defaults set up along with examples of how a fully functioning eCommerce site based on Broadleaf might work.

One typical way of using this project would be to follow the [Getting Started Guide](http://docs.broadleafcommerce.org/current/Getting-Started.html), which would walk you through using our pre-bundled Eclipse workspace.

However, if you would like to utilize your own workspace or IDE configuration, you may prefer to fork this project. This would give you the added benefit of being able to pull in upstream changes as we work to improve the DemoSite.

> Note: If you are going to fork this project, we recommend basing your work on the `master` branch, and not the develop branch. develop is our ongoing development branch and there are no guarantees of stability on it.

## API
### 1. Common
#### 1.1 Exception
If a exception is thrown while processing the request, the server will return a 500 response containing a header 'ErrorCode'

### 2. Customer

#### 2.1 Request
POST /api/v1/customers/register
```json
{
  "name":"XXX",
  "email":"XXX",
  "password":"xxx",
  "passwordConfirm":"xxx"
}
```
#### 2.2 Response
```json
{
  "id":"123",
  "firstName":"name",
  "email":"email"
}
```

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

