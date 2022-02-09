from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name='index'),

    path('doctors', views.DoctorAPI.as_view(), name='doctors'),
    path('doctors/<int:pk>', views.DoctorAPI.as_view(), name='doctor'),
    path('doctors/<slug:name>', views.DoctorAPI.as_view(), name='namelikedoctor'),

    path('appointments', views.AppointmentAPI.as_view(), name='appointments'),
    path('appointments/<int:pk>', views.AppointmentAPI.as_view(), name='appointment'),

    path('medicines', views.MedicineAPI.as_view(), name='medicines'),
    path('medicines/<int:pk>', views.MedicineAPI.as_view(), name='medicine'),
    path('medicines/<slug:name>', views.MedicineAPI.as_view(), name='namelikemedicine'),

    path('blogs', views.BlogAPI.as_view(), name='blogs'),
    path('blogs/<int:pk>', views.BlogAPI.as_view(), name='blog'),

    path('users', views.UsersAPI.as_view(), name='users'),
    path('users/<int:pk>', views.UsersAPI.as_view(), name='user'),
    path('users/<email>/<password>', views.UsersAPI.as_view(), name='isadminuser'),

    path('orders', views.OrderAPI.as_view(), name='orders'),
    path('orders/<int:pk>', views.OrderAPI.as_view(), name='orders'),

    path('ordermedicine', views.OrderMedicineAPI.as_view(), name='ordermedicines'),
    path('ordermedicine/<int:pk>', views.OrderMedicineAPI.as_view(), name='ordermedicine'),

]
