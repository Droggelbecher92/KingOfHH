# Deploy Template

## Try it out
- Here you go: https://razor-ramon.herokuapp.com/

## - What you need:

- [Heroku account](https://www.heroku.com/)
- [MongoDB Atlas](https://www.mongodb.com/atlas/database)

## - What to do:


### Deploy

- Clone this Repo and connect it with a new Repo of yours(you can delete the .git Folder and make a new git init).
- Install or sign up to Heroku and MongoDB using the Links above.


- MongoDB Atlas:
    - create a Cluster for your App (check the pricing, there is a free tier)
    - After you created it, use the connect button => connect your application
      => java/current version => copy the connection string
- Let´s head to Heroku:
    - Create an App for your application
    - Head to settings and reveal Config Vars
    - Add the Key `URI` and as Value paste in your connetion string (replace user
      and password with your values)
    - Also add under `JWT_SECRET` a strong secret for your JWT.
    - Since we are already on Heroku, we need some kind of authentication for
      GitHub, in this case an API-Key. We can generate our API-Key in Heroku by clicking the
      User-icon (top-right) => Account settings => scroll down untill you see API-Key
    - Either generate or copy your API-Key.
    - Thats all you have to do on Heroku. Everything else is done by the CI in combination with the API-Key.
- Github:
    - We have save this API-Key in our Repo, to Login to Heroku.
    - Go to your Repo => Settings => Secrets => Actions => New Repository Secret
    - Save your API-Key as `HEROKU_API_KEY`, save, and now GitHub and Heroku can communicate.
    

## - What to change:

- Security-stuff:
    - Update the `MyUser` Model to you liking.
    - Change the payload fpr your JWT to your liking. Currently the Username is set as sub, claims have iat/epx Date and roles. 

- pom.xml:
  - change the finalName to your Appname.
  - You can change the groupId if you like.

- Github Action:
  - All points of change are marked inside th action.

- Dockerfile:
  - Please change the Label :D
  - The .jar name should be changed to the same name, as in the pom.xml under 'finalName' (at three places, you´ll manage to do it!)

- application.properties:
  - You can change the DB name for your local MongoDB here.

- You can remove all Packages in the backend, they are just an example for the DB connection.

## - Problems
- If you encounter any problems using this template, please let me know by stating an issue on GitHub.

