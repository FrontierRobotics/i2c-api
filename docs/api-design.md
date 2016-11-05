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

## GET /bus/{bus}/address/{address}/internal_address/{internal_address}

Response Body:

```
{
  "data": "string"
}
```

Path Parameters:

|Name             |Type   |Required|
|-----------------|-------|--------|
|bus              |integer|     yes|
|address          |integer|     yes|
|internal_address |integer|      no|

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

## PUT /bus/{bus}/address/{address}/internal_address/{internal_address}

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

|Name             |Type   |Required|
|-----------------|-------|--------|
|bus              |integer|     yes|
|address          |integer|     yes|
|internal_address |integer|      no|

Query Parameters:

|Name |Type   |Required|
|-----|-------|--------|
|size |integer|      no|
