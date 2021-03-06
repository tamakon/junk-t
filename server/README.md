# How to start in the local environment

This Project uses `pyenv` and `pipenv`. Please install it with `Homebrew` or something.
Then, install.
```
cd junk-t/server
pipenv install
```
This command installs all of the packages specified in Pipfile and python of the version specified in Pipfile.

Next, starting database server. This project uses MySQL. So you need to start MySQL server. 
It is several way but we give an example only Homebrew's case. 
```
brew install mysql
brew services start mysql
```
Next, creating database and user along django's `DATABASE` setting.
```
mysql -u root -p
mysql > create user 'junk-t'@'localhost' identified by 'junk-t-pass';
mysql > grant all privileges on *.* to 'junk-t'@'localhost';
mysql > create database junktion;
```

Execute Django commands after entering to `pipenv`'s virtual environment.
```
pipenv shell
python manage.py migrate
python manage.py runserver
```
If success, you can access via http://localhost:8000.

# How to start in the local environment (Docker)
This way assumes Docker has already installed. If do not, please install it.  

And this way uses docker-sync for development productivity. 
So you need to install `docker-sync` and need to install `unison` used as sync strategy too.
About way to install, please see [document of docker-sync installation](https://github.com/EugenMayer/docker-sync/wiki/1.-Installation).

Then execute below command. 
If building is not needed, omit `--build` option.
```
cd junk-t
docker-sync start
docker-compose up --detach --build 
```
If success, you can access via http://localhost:8000/srv/.

You can see log by using below command.
```
docker-compose logs --follow
```

When you want to down docker-compose and docker-sync, just do below.  
```
docker-compose down
docker-sync stop
```
