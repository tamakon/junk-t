from rest_framework import serializers

from api.models import Image


class ImageSerializer(serializers.HyperlinkedModelSerializer):
    url = serializers.SerializerMethodField()

    class Meta:
        model = Image
        fields = ("tag", "update_at", "url")

    def get_url(self, model):
        request = self.context["request"]
        scheme = request.scheme
        host = request.get_host()
        return "{0}://{1}/api/resource/images/{2}".format(scheme, host, model.tag)
