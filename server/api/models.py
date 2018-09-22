from django.db import models


def image_file_path(instance, filename):
    """
    :param instance: An instance of the model where the FileField is defined.
    :param filename: uploaded file name.
    :return: path that image file will be stored.
    """
    return "api/resource/images/{0}_{1}".format(instance.tag, filename)


class Image(models.Model):

    tag = models.CharField(max_length=200, primary_key=True)
    "key of image file"
    resource = models.ImageField(upload_to=image_file_path)
    "image file itself"
    create_at = models.DateTimeField(auto_now_add=True)
    "date and minute that model has created"
    update_at = models.DateTimeField(auto_now=True)
    "date and minute that model has last updated"
