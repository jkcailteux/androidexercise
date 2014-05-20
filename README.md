
Knoda Android Developer Exercise
===============
<p align="center">
  <img src="http://imgur.com/KtGz6ju.png" style="width:200px;height:200px"/>
</p>

General Instructions
--------------------

1. Fork this repository.
2. Implement your version of our home screen (see below).
3. Commit your solution to your fork, and shoot us an email.



Things you'll need
-------------------

1. REST API endpoints for getting a list of the most recent predictions:
Method: GET<br>
URL: http://api.knoda.com/api/predictions.json?recent=true&auth_token={token}<br>
Headers:<br>
	Content-Type: application/json; charset=utf-8;<br>
	Accept: application/json; api_version=3;<br>
2. The project contains all the assets you will need in order to build out most of the UI.
3. Quick JSON field descriptions

```javascript

    {
        "id": 6513,
        //Body of the prediction.
        "body": "Billy Butler will win at least one AL Player of the Week awards before the season ends. ",
        //Meta information that will be useful to you.
        "agreed_count": 1,
        "disagreed_count": 2,
        "comment_count": 0,
        //Timestamps
        "expires_at": "2014-07-04T05:00:00.000Z",
        "created_at": "2014-05-19T05:28:38.427Z",
        //User information
        "user_id": 1482,
        "username": "canglem",
        "user_avatar": {
            "big": "http://knoda.s3.amazonaws.com/users/avatars/000/001/482/big/image.png?1391287169",
            "small": "http://knoda.s3.amazonaws.com/users/avatars/000/001/482/small/image.png?1391287169",
            "thumb": "http://knoda.s3.amazonaws.com/users/avatars/000/001/482/thumb/image.png?1391287169"
        },
        "verified_account": false,
        //Information about the current user's vote on the prediction
        "my_challenge": {
            "agree": false,
        }
    }

```


### FAQ

Q: Do I need to include a pull to refresh implementation, or the side menu?<br>
A: No, all we are looking for you to do in this exercise is to build a simple list of predictions, and style it like our live apps.
You are, of course, free to show us how you'd build any of the additional features in our app.

Q: I'm having trouble with Android Studio / The REST API isn't giving me what I expected.<br>
A: Shoot us an email, we're happy to answer any questions you may have.


### Screenshot

<p align="center">
  <img src="http://imgur.com/ZJAjsm4.png"/>
</p>
