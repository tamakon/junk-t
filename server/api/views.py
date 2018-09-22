from django.http import FileResponse
from django.shortcuts import get_object_or_404
from rest_framework import viewsets, mixins

from api.models import Image
from api.serializers import ImageSerializer


class ImageViewSet(mixins.ListModelMixin, viewsets.GenericViewSet):
    queryset = Image.objects.all()
    serializer_class = ImageSerializer


def image_content(request, tag):
    image = get_object_or_404(Image, tag=tag)
    return FileResponse(image.resource)
