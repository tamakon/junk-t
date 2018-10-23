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
        script_name = request.META.get('SCRIPT_NAME')
        return "{0}://{1}{2}/api/resource/images/{3}".format(scheme, host, script_name, model.tag)
