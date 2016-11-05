## GET /bus/{bus}/address/{address}

Response Body:

```
{
  "data": "string"
}
```

Path Parameters:

|Name    |Type   |Required|
|--------|-------|--------|
|bus     |integer|     yes|
|address |integer|     yes|

## PUT /bus/{bus}/address/{address}

Request Body:

```
{
  "data": "string"
}
```

Response Body:

```
no content
```

Path Parameters:

|Name    |Type   |Required|
|--------|-------|--------|
|bus     |integer|     yes|
|address |integer|     yes|

Query Parameters:

|Name |Type   |Required|
|-----|-------|--------|
|size |integer|      no|
