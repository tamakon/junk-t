from django.db import models


def image_file_path(instance, _):
    """
    :param instance: An instance of the model where the FileField is defined.
    :param _: uploaded file name. but it is not used.
    :return: path that image file will be stored.
    """
    return "api/resource/images/{0}".format(instance.tag)


class Image(models.Model):

    tag = models.CharField(max_length=200, primary_key=True)
    "key of image file"
    resource = models.ImageField(upload_to=image_file_path)
    "image file itself"
    create_at = models.DateTimeField(auto_now_add=True)
    "date and minute that model has created"
    update_at = models.DateTimeField(auto_now=True)
    "date and minute that model has last updated"
