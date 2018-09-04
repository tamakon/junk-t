# How to start in the local environment

This Project uses `pyenv` and `pipenv`. Please install it with `Homebrew` or something.
Then, install.
```
cd junk-t/server
pipenv install
```
This command installs all of the packages specified in Pipfile and python of the version specified in Pipfile.

Execute Django commands after entering to `pipenv`'s virtual environment.
```
pipenv shell
python manage.py migrate
python manage.py runserver
```
If success, you can access via http://localhost:8000.

# How to start in the local environment (Docker)
Just execute below command. This way assumes Docker has already installed.
```
cd junk-t
docker-compose up 
```
If success, you can access via http://localhost:8000.

You can see log by using below command.
```
docker-compose logs --follow
```

When you want to down docker-compose, just do below.
```
docker-compose down
``` 
