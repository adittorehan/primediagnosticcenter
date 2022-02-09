# Generated by Django 4.0.1 on 2022-01-12 20:47

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Blog',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('heading', models.CharField(max_length=200)),
                ('body', models.TextField()),
                ('photo', models.ImageField(null=True, upload_to='images/')),
            ],
        ),
        migrations.AlterField(
            model_name='doctor',
            name='photo',
            field=models.ImageField(null=True, upload_to='images/'),
        ),
    ]