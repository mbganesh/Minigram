# Minigram
> Its a kind of Instagram clone app 

* Front-End   :   Java
* Back-End    :   Express JS
* Database    :   MongoDB

## Android

```

Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.27:1111/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                
@GET("api/show-post")
Call<List<PostModel>> getPost();                
                
                
```                

## post.json

> Hit to open post.json file  [Here](https://github.com/mbganesh/Minigram/blob/master/post.json).


## MongoDB

```
 db.user_posts.insertMany( PASTE  POST JSON  )
 
 ```
 
 

## Express
```

router.get("/show-post", async (req, res) => {
    var posts = await PostModel.find({}, { __v: 0, _id: 0 });
    res.json(posts);
});

```
