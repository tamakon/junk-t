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
