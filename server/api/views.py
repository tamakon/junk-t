from rest_framework import viewsets
from rest_framework.response import Response

from api.models import Image
from api.serializers import ImageSerializer


class ImageViewSet(viewsets.ViewSet):

    # noinspection PyMethodMayBeStatic
    def list(self, request):
        queryset = Image.objects.all()
        serializer = ImageSerializer(queryset, many=True, context={'request': request})
        return Response(serializer.data)
