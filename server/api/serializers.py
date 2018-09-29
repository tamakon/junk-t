from rest_framework import serializers

from api.models import Image


class ImageSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Image
        fields = ("tag", "update_at")
