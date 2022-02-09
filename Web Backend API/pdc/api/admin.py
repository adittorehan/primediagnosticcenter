from django.contrib import admin
from .models import *


@admin.register(Doctor)
class DoctorAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'department', 'designation', 'photo']


@admin.register(Blog)
class BlogAdmin(admin.ModelAdmin):
    list_display = ['id', 'heading', 'photo']


@admin.register(Appointment)
class AppointmentAdmin(admin.ModelAdmin):
    list_display = ['id', 'doctor', 'user_email', 'user_phone', 'date', 'status']


@admin.register(Users)
class UsersAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'email', 'admin_status']


@admin.register(Medicine)
class MedicineAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'detail', 'price', 'photo']


@admin.register(Order)
class OrderAdmin(admin.ModelAdmin):
    fields = ('email', 'address', 'phone', 'status')
    actions = ['cancel_order', 'ship_order', 'complete_order']

    @admin.action(description='Cancel order status')
    def cancel_order(self, request, queryset):
        queryset[0].set_order_status(1)

    @admin.action(description='Ship order')
    def ship_order(self, request, queryset):
        queryset[0].set_order_status(2)

    @admin.action(description='Complete Order')
    def complete_order(self, request, queryset):
        queryset[0].set_order_status(3)



@admin.register(OrderMedicine)
class OrderMedicineAdmin(admin.ModelAdmin):
    fields = ['medicine', 'order', 'quantity']
