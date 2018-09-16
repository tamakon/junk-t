from rest_framework import serializers

from api.models import Image


class ImageSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Image
        # Comma is needed because 'fields' field must be as tuple.
        # If no comma, `fields` is converted to string type.
        fields = ("tag",)
