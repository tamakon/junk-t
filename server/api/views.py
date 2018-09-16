from rest_framework import viewsets, mixins

from api.models import Image
from api.serializers import ImageSerializer


class ImageViewSet(mixins.ListModelMixin, viewsets.GenericViewSet):
    queryset = Image.objects.all()
    serializer_class = ImageSerializer
