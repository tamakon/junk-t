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
If success, you can access via http:localhost:8000.

# How to start in the local environment (Docker)
Just execute below command. This way assumes Docker has already installed.
```
cd junk-t
docker build -t junk-t-image . && docker run --name junk-t --detach -p 8000:8000 junk-t-image 
```
If success, you can access via http:localhost:8000.
