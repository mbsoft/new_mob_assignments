from django.db import models


class SkateBoard(models.Model):
    owner = models.CharField(max_length=70)
    brand = models.CharField(max_length=70)
    weight = models.FloatField()
    location = models.CharField(max_length=70)
